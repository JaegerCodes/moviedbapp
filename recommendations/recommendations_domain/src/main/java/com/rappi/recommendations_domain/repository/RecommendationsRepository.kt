package com.rappi.recommendations_domain.repository

import com.rappi.core.domain.model.Resource
import com.rappi.recommendations_domain.model.RecommendationsMoviesDetail

interface RecommendationsRepository {
    suspend fun recommendationsMovies(
        page: Int,
        language: String
    ): Resource<RecommendationsMoviesDetail>
}