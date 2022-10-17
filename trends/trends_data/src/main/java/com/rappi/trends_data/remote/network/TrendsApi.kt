package com.rappi.trends_data.remote.network

import com.rappi.trends_data.remote.dto.TrendsDto
import com.rappi.core.BuildConfig
import retrofit2.http.GET

interface TrendsApi {
    @GET("3/movie/top_rated?language=en-US&page=1")
    suspend fun getUpcomingMovies(): TrendsDto

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
    }
}