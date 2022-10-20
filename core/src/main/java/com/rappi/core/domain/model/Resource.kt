package com.rappi.core.domain.model

sealed class Resource<out T> {

    data class Success<out T> (val data: T) : Resource<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()

    object Empty : Resource<Nothing>()
}