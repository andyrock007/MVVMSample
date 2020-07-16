package com.kc.sampledemo.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ")
        .build()
        return chain.proceed(newRequest)
    }

}