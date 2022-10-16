package com.rappi.upcoming_data.remote.dto

data class UpcomingDto (
    val dates: DatesDto,
    val page: Long,
    val results: List<ResultDto>,
    val totalPages: Long,
    val totalResults: Long
)
