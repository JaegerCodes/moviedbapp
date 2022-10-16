package com.rappi.upcoming_data.remote.network

import com.rappi.upcoming_data.remote.dto.upcomingDto
import com.rappi.core.BuildConfig
import retrofit2.http.GET

interface upcomingApi {
    @GET("3/movie/upcoming?language=en-US&page=1")
    suspend fun getupcoming(): upcomingDto

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
    }
}