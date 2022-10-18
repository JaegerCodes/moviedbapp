package com.rappi.trends_data.repository

import com.rappi.core.domain.network.SafeApiCall
import com.rappi.core.domain.model.Resource
import com.rappi.trends_data.mapper.toTrendsMoviesDetail
import com.rappi.trends_data.remote.network.TrendsApi
import com.rappi.trends_domain.model.TrendsMoviesDetail
import com.rappi.trends_domain.repository.TrendsRepository

class TrendsRepositoryImpl(
    private val api: TrendsApi
): TrendsRepository, SafeApiCall {
    override suspend fun trendsMovies(): Resource<TrendsMoviesDetail> = safeApiCall {
        val trendsMoviesDto = api.getTrendsMovies()
        trendsMoviesDto.toTrendsMoviesDetail()
    }
}