package com.rappi.core.presentation.ui_extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.Fragment

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

fun Fragment.openYoutubeVideo(videoKey: String) {
    val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoKey"))
    val webIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("http://www.youtube.com/watch?v=$videoKey")
    )
    try {
        startActivity(appIntent)
    } catch (ex: ActivityNotFoundException) {
        startActivity(webIntent)
    }
}
