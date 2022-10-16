package com.rappi.upcoming_data.remote.dto

import com.squareup.moshi.Json

data class Upcoming (
    val adult: Boolean?,
    @Json(name="backdrop_path")
    val backdropPath: String?,
    @Json(name="genre_ids")
    val genreIDS: List<Long?>?,
    val id: Long?,
    @Json(name="original_Language")
    val originalLanguage: String?,
    @Json(name="original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @Json(name="poster_path")
    val posterPath: String?,
    @Json(name="release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @Json(name="vote_average")
    val voteAverage: Double?,
    @Json(name="vote_count")
    val voteCount: Long?
)
