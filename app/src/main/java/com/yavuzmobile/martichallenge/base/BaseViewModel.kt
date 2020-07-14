package com.yavuzmobile.martichallenge.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yavuzmobile.martichallenge.model.ApiResponse
import com.yavuzmobile.martichallenge.model.YError
import kotlinx.coroutines.launch


open class BaseViewModel : ViewModel() {

    val baseErrorLive = SingleLiveEvent<YError>()


    fun postError(err: YError) = baseErrorLive.postValue(err)

    fun <T> callService(
        service: suspend () -> ApiResponse<T>,
        success: (response: T) -> Unit
    ) {
        viewModelScope.launch {
            val response = service()
            if (response.success != null) {
                success(response.success)
            }
        }
    }
}