package com.rappi.recommendations_data.remote.dto

import com.squareup.moshi.Json

data class RecommendationsDto (
    val dates: Dates?,
    val page: Int?,
    val results: List<Recommendations?>?,
    @field:Json(name="total_pages")
    val totalPages: Long?,
    @field:Json(name="total_results")
    val totalResults: Long?
)
