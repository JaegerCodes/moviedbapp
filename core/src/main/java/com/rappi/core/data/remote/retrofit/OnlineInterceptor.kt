package com.rappi.core.data.remote.retrofit

import okhttp3.Interceptor
import okhttp3.Response

object OnlineInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val maxAge = 20

        return response.newBuilder()
            .header("Cache-control", "public, max-age=$maxAge")
            .build()
    }
}