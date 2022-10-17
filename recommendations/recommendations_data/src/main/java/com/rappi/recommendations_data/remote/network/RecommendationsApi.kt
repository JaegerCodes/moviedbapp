package com.rappi.recommendations_data.remote.network

import com.rappi.recommendations_data.remote.dto.RecommendationsDto
import com.rappi.core.BuildConfig
import retrofit2.http.GET

interface RecommendationsApi {
    @GET("3/movie/top_rated?language=en-US&page=1")
    suspend fun getRecommendationsMovies(): RecommendationsDto

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
    }
}