package com.rappi.moviedetail_data.remote.dto

import com.squareup.moshi.Json
import java.util.Date

data class MovieDetailDto (
    val adult: Boolean,

    @field:Json(name = "backdrop_path")
    val backdropPath: String,

    @field:Json(name = "belongs_to_collection")
    val belongsToCollection: Any? = null,

    val budget: Long,
    val genres: List<Genre?>?,
    val homepage: String,
    val id: Long,

    @field:Json(name = "imdb_id")
    val imdbID: String,

    @field:Json(name = "original_language")
    val originalLanguage: String,

    @field:Json(name = "original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>,

    @field:Json(name = "production_countries")
    val productionCountries: List<ProductionCountry>,

    @field:Json(name = "release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @field:Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @field:Json(name = "vote_average")
    val voteAverage: Double,

    @field:Json(name = "vote_count")
    val voteCount: Long
)







