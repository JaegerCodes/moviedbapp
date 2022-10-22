package com.rappi.moviedetail_data.remote.network

import com.rappi.moviedetail_data.remote.dto.MovieDetailDto
import com.rappi.moviedetail_data.remote.dto.MovieVideosDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailApi {
    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieID: String,
        @Query("language") language: String
    ): MovieDetailDto

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") movieID: String,
    ): MovieVideosDto
}