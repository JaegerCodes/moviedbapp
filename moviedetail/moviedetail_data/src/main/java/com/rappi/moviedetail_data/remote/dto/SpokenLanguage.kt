package com.rappi.moviedetail_data.remote.dto

import com.squareup.moshi.Json

data class SpokenLanguage (
    @field:Json(name = "english_name")
    val englishName: String,

    @field:Json(name = "iso_639_1")
    val iso639: String,

    val name: String
)