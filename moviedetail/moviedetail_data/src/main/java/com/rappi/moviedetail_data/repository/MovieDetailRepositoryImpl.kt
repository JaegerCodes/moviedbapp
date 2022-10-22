package com.rappi.moviedetail_data.repository

import com.rappi.core.domain.model.Resource
import com.rappi.core.domain.network.SafeApiCall
import com.rappi.moviedetail_data.mapper.toMovieDetail
import com.rappi.moviedetail_data.mapper.toMovieVideo
import com.rappi.moviedetail_data.remote.network.MovieDetailApi
import com.rappi.moviedetail_domain.model.MovieDetail
import com.rappi.moviedetail_domain.model.MovieVideo
import com.rappi.moviedetail_domain.repository.MovieDetailRepository

class MovieDetailRepositoryImpl(
    private val api: MovieDetailApi
): MovieDetailRepository, SafeApiCall {
    override suspend fun movieDetail(movieID: String, language: String): Resource<MovieDetail> = safeApiCall {
        api.getMovieDetail(movieID, language).toMovieDetail()
    }

    override suspend fun getMovieVideo(movieID: String): Resource<MovieVideo> = safeApiCall {
        api.getMovieVideo(movieID).toMovieVideo()
    }
}