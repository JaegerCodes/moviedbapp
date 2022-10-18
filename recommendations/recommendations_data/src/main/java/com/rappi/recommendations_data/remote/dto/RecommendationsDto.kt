package com.rappi.recommendations_data.remote.dto

import com.squareup.moshi.Json

data class RecommendationsDto (
    val dates: Dates?,
    val page: Long?,
    val results: List<Recommendations?>?,
    @Json(name="total_pages")
    val totalPages: Long?,
    @Json(name="total_results")
    val totalResults: Long?
)
