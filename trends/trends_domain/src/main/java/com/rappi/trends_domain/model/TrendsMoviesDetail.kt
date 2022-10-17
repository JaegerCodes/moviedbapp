package com.rappi.trends_domain.model

data class TrendsMoviesDetail (
    val page: Long,
    val trends: List<TrendsMovie>,
    val totalPages: Long,
)
