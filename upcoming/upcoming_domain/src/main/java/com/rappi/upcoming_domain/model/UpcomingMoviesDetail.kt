package com.rappi.upcoming_domain.model

import com.rappi.core.domain.model.DMovieDetail

data class UpcomingMoviesDetail (
    override val page: Int,
    override val movies: List<UpcomingMovie>,
    override val totalPages: Long,
): DMovieDetail
