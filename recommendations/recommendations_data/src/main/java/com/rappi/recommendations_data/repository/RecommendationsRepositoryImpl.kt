package com.rappi.recommendations_data.repository

import com.rappi.core.domain.network.SafeApiCall
import com.rappi.core.domain.model.Resource
import com.rappi.recommendations_data.mapper.toTrendsMoviesDetail
import com.rappi.recommendations_data.remote.network.RecommendationsApi
import com.rappi.recommendations_domain.model.RecommendationsMoviesDetail
import com.rappi.recommendations_domain.repository.RecommendationsRepository

class RecommendationsRepositoryImpl(
    private val api: RecommendationsApi
): RecommendationsRepository, SafeApiCall {
    override suspend fun recommendationsMovies(
        page: Int,
        language: String
    ): Resource<RecommendationsMoviesDetail> = safeApiCall {
        val trendsMoviesDto = api.getRecommendationsMovies(page, language)
        trendsMoviesDto.toTrendsMoviesDetail()
    }
}