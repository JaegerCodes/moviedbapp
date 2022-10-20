package com.rappi.trends_domain.repository

import com.rappi.core.domain.model.Resource
import com.rappi.trends_domain.model.TrendsMoviesDetail

interface TrendsRepository {
    suspend fun trendsMovies(
        page: Int,
        language: String
    ): Resource<TrendsMoviesDetail>
}