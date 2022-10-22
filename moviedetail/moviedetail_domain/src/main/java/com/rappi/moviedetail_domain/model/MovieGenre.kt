package com.rappi.moviedetail_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieGenre (
    val id: Long,
    var name: String
): Parcelable