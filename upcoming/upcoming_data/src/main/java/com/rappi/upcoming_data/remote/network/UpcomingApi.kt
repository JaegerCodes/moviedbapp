package com.rappi.upcoming_data.remote.network

import com.rappi.upcoming_data.remote.dto.UpcomingDto
import com.rappi.core.BuildConfig
import com.rappi.core.presentation.model.Languages
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingApi {
    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("language") language: String = Languages.EsES.name
    ): UpcomingDto

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
    }
}