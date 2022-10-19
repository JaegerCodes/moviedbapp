package com.rappi.recommendations_domain.model

import com.rappi.core.domain.model.DMovieDetail

data class RecommendationsMoviesDetail (
    override val page: Int,
    override val movies: List<RecommendationsMovie>,
    override val totalPages: Long,
): DMovieDetail
