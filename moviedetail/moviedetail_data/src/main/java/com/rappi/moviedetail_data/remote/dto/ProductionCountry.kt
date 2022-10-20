package com.rappi.moviedetail_data.remote.dto

import com.squareup.moshi.Json

data class ProductionCountry (
    @field:Json(name = "iso_3166_1")
    val iso3166: String,

    val name: String
)