package com.rappi.core.presentation.ui_extensions

import com.rappi.core.BuildConfig


sealed class PosterSize {
    object Medium: PosterSize() {
        public override fun url(path: String) = "${baseUrl}w400/$path"
    }
    object Large: PosterSize() {
        public override fun url(path: String) = "${baseUrl}w500/$path"
    }

    internal abstract fun url(path: String): String
    internal val baseUrl: String = BuildConfig.TMDB_IMAGES_URL
}