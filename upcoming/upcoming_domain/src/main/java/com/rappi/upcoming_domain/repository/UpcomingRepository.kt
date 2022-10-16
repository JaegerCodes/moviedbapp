package com.rappi.upcoming_domain.repository

import com.rappi.core.domain.Resource
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail

interface UpcomingRepository {
    suspend fun upcomingMovies(): Resource<UpcomingMoviesDetail>
}