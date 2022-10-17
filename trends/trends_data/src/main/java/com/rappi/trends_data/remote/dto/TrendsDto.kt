package com.rappi.trends_data.remote.dto

import com.squareup.moshi.Json

data class TrendsDto (
    val dates: Dates?,
    val page: Long?,
    val results: List<TrendsApi?>?,
    @Json(name="total_pages")
    val totalPages: Long?,
    @Json(name="total_results")
    val totalResults: Long?
)
