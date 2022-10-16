package com.rappi.upcoming_data.repository

import com.rappi.core.domain.SafeApiCall
import com.rappi.core.domain.Resource
import com.rappi.upcoming_data.mapper.toUpcomingMoviesDetail
import com.rappi.upcoming_data.remote.network.UpcomingApi
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail
import com.rappi.upcoming_domain.repository.UpcomingRepository

class UpcomingRepositoryImpl(
    private val api: UpcomingApi
): UpcomingRepository, SafeApiCall {
    override suspend fun upcomingMovies(): Resource<UpcomingMoviesDetail> = safeApiCall {
        val upcomingMoviesDto = api.getUpcomingMovies()
        upcomingMoviesDto.toUpcomingMoviesDetail()
    }
}