package com.yavuzmobile.martichallenge.di

import com.yavuzmobile.martichallenge.network.Api
import com.yavuzmobile.martichallenge.network.ApiInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val baseUrl = "https://maps.googleapis.com/maps/api/"

val networkModule = module {

    single { GsonProvider.get() }

    single { ApiInterceptor() }

    single {
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(get<ApiInterceptor>())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single<Api> {
        get<Retrofit>().create(Api::class.java)
    }
}