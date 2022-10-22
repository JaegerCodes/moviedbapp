package com.rappi.moviedetail_data.remote.dto

import com.squareup.moshi.Json

data class MovieVideosDto (
    val id: Long,
    val results: List<MovieVideo?>
)

data class MovieVideo (
    @field:Json(name = "iso_639_1")
    val iso639: String,

    @field:Json(name = "iso_3166_1")
    val iso3166: String,

    val name: String?,
    val key: String?,
    val site: String?,
    val size: Long?,
    val type: String?,
    val official: Boolean?,

    @field:Json(name = "published_at")
    val publishedAt: String,

    val id: String
)
