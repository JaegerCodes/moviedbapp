package com.rappi.core.presentation.ui_extensions

import android.view.View
import android.widget.ProgressBar

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
