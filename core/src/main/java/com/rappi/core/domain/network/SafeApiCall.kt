package com.rappi.core.domain.network

import com.rappi.core.BuildConfig
import com.rappi.core.domain.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = Resource.Success(apiCall.invoke())
                response
            } catch (throwable: Throwable) {

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