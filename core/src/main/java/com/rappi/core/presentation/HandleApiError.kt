package com.rappi.core.presentation

import androidx.fragment.app.Fragment
import com.rappi.core.domain.Resource

fun Fragment.handleApiError(
    failure: Resource.Failure,
    onRequestFail: () -> Unit = {}
) {
    when {
        failure.isNetworkError -> {
            onRequestFail.invoke()
        }
        failure.errorCode == 401 -> {
            // Todo: redirect to Login page
        }
        else -> Unit
    }
}