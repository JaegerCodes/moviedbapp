package com.rappi.upcoming_domain.repository

import com.rappi.core.domain.model.Resource
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail

interface UpcomingRepository {
    suspend fun upcomingMovies(
        page: Int,
        language: String
    ): Resource<UpcomingMoviesDetail>
}