package com.rappi.moviedetail_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class MovieDetail(
    val plot: String,
    val genrs: List<MovieGenre>,
    val releaseDate: Date,
    val originalLanguage: String,
    val voteAverage: String,
    val posterPath: String,
    val title: String,
): Parcelable