package com.rappi.core.presentation.ui_extensions

import androidx.fragment.app.Fragment
import com.rappi.core.domain.model.Resource

fun Fragment.handleApiError(
    failure: Resource.Failure,
    onIsNetworkError: () -> Unit = {},
) {
    when {
        failure.isNetworkError -> {
            onIsNetworkError.invoke()
        }
        failure.errorCode == 401 -> {
            // Todo: redirect to Login page
        }
        else -> Unit
    }
}