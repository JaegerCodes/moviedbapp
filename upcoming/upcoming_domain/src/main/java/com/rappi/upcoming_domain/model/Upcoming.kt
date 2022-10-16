package com.rappi.upcoming_domain.model

data class Upcoming (
    val page: Long,
    val upcoming: List<UpcomingMovie>,
    val totalPages: Long,
    val totalResults: Long
)
