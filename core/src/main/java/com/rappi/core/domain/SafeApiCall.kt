package com.rappi.core.domain

import com.rappi.core.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = Resource.Success(apiCall.invoke())
                response
            } catch (throwable: Throwable) {
                if (BuildConfig.DEBUG) {
                    android.util.Log.w("Throwable error", throwable.message ?: "")
                }
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code())
                    }
                    else -> {
                        Resource.Failure(true, null)
                    }
                }
            }
        }
    }

}