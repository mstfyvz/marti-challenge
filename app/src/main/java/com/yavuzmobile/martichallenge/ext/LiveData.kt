package com.yavuzmobile.martichallenge.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.yavuzmobile.martichallenge.di.GsonProvider
import com.yavuzmobile.martichallenge.model.ApiResponse
import com.yavuzmobile.martichallenge.model.ResponseError
import com.yavuzmobile.martichallenge.model.YError
import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


fun MutableLiveData<Boolean>.postTrue() = postValue(true)

fun MutableLiveData<Boolean>.postFalse() = postValue(false)

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    observe(owner, Observer {
        it?.apply {
            observer(this)
        }
    })
}

suspend fun <T> apiWrapper(service: suspend () -> Response<T>): ApiResponse<T> {
    return try {
        val response = service()
        if (response.isSuccessful) {
            ApiResponse(response.body())
        } else {
            when (response.code()) {
                401 -> {
                    ApiResponse(error = YError.Authorization)
                }
                else -> {
                    val errorString = response.errorBody()?.string()
                    return try {
                        val responseError = GsonProvider.get().fromJson(errorString, ResponseError::class.java)
                        if (!responseError?.errorMessage.isNullOrEmpty()) {
                            ApiResponse(error = YError.Business(responseError!!.errorMessage!!))
                        } else {
                            ApiResponse(error = YError.Generic)
                        }
                    } catch (e: Exception) {
                        ApiResponse(error = YError.Generic)
                    }
                }
            }
        }

    } catch (e: Exception) {
        when (e) {
            is UnknownHostException -> ApiResponse(error = YError.Network)
            is TimeoutException -> ApiResponse(error = YError.Timeout)
            else -> ApiResponse(error = YError.Network)
        }

    }
}