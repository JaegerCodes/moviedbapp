package com.rappi.moviedetail_data.remote.dto

import com.squareup.moshi.Json

data class ProductionCompany (
    val id: Long,

    @field:Json(name = "logo_path")
    val logoPath: String? = null,

    val name: String,

    @field:Json(name = "origin_country")
    val originCountry: String
)