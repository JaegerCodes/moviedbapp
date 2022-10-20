package com.rappi.core.presentation.ui_extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.visible(isVisible: Boolean, isWithAnimation: Boolean = false) {
    val currentAlpha = alpha
    if (!isVisible && isWithAnimation) {
        animate()
            .alpha(0f)
            .setDuration(1500L)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    visibility = View.GONE
                    alpha = currentAlpha
                }
            })
    } else {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
