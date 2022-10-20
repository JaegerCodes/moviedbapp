package com.rappi.core.data.remote.retrofit

import android.content.Context
import com.rappi.core.app.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response

class OfflineInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!context.isNetworkAvailable()) {
            val maxStale = 60 * 60 * 24 * 3
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
        return chain.proceed(request)
    }
}