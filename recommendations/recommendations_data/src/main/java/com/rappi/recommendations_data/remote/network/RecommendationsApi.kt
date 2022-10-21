package com.rappi.recommendations_data.remote.network

import com.rappi.recommendations_data.remote.dto.RecommendationsDto
import com.rappi.core.app.Languages
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendationsApi {
    @GET("3/movie/top_rated")
    suspend fun getRecommendationsMovies(
        @Query("page") page: Int,
        @Query("language") language: String = Languages.EsES.name
    ): RecommendationsDto
}