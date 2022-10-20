package com.rappi.trends_data.remote.network

import com.rappi.trends_data.remote.dto.TrendsDto
import com.rappi.core.BuildConfig
import com.rappi.core.presentation.model.Languages
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendsApi {
    @GET("3/movie/top_rated")
    suspend fun getTrendsMovies(
        @Query("page") page: Int,
        @Query("language") language: String = Languages.EnUs.name
    ): TrendsDto
}