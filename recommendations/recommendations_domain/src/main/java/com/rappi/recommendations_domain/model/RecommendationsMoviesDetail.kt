package com.rappi.recommendations_domain.model

data class RecommendationsMoviesDetail (
    val page: Long,
    val recommendations: List<RecommendationsMovie>,
    val totalPages: Long,
)
