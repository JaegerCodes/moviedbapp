package com.rappi.upcoming_data.remote.dto

import com.squareup.moshi.Json

data class UpcomingDto (
    val dates: Dates?,
    val page: Int?,
    val results: List<Upcoming?>?,
    @Json(name="total_pages")
    val totalPages: Long?,
    @Json(name="total_results")
    val totalResults: Long?
)
