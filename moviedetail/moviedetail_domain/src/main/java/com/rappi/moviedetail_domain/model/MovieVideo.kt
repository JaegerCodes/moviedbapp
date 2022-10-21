package com.rappi.moviedetail_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieVideo(
    val name: String,
    val key: String,
    val site: String,
    val id: String,
): Parcelable