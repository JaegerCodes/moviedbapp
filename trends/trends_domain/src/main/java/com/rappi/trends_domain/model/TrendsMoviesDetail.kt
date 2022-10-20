package com.rappi.trends_domain.model

import com.rappi.core.domain.model.DMovieDetail

data class TrendsMoviesDetail (
    override val page: Int,
    override val movies: List<TrendsMovie>,
    override val totalPages: Long,
): DMovieDetail
