package com.yavuzmobile.martichallenge.network

import okhttp3.Interceptor
import okhttp3.Response


class ApiInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder().build()
        return chain.proceed(newRequest)
    }
}