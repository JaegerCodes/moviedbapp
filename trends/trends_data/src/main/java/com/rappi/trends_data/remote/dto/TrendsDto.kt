package com.rappi.trends_data.remote.dto

import com.squareup.moshi.Json

data class TrendsDto (
    val dates: Dates?,
    val page: Int?,
    val results: List<Trends?>?,
    @field:Json(name="total_pages")
    val totalPages: Long?,
    @field:Json(name="total_results")
    val totalResults: Long?
)
