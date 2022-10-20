package com.rappi.upcoming_data.repository

import com.rappi.core.domain.network.SafeApiCall
import com.rappi.core.domain.model.Resource
import com.rappi.upcoming_data.mapper.toUpcomingMoviesDetail
import com.rappi.upcoming_data.remote.network.UpcomingApi
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail
import com.rappi.upcoming_domain.repository.UpcomingRepository

class UpcomingRepositoryImpl(
    private val api: UpcomingApi
): UpcomingRepository, SafeApiCall {
    override suspend fun upcomingMovies(
        page: Int,
        language: String
    ): Resource<UpcomingMoviesDetail> = safeApiCall {
        val upcomingMoviesDto = api.getUpcomingMovies(page, language)
        upcomingMoviesDto.toUpcomingMoviesDetail()
    }
}