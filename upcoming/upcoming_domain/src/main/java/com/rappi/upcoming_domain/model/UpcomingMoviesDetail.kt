package com.rappi.upcoming_domain.model

data class UpcomingMoviesDetail (
    val page: Int,
    val upcoming: List<UpcomingMovie>,
    val totalPages: Long,
)
