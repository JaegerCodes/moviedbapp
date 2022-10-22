package com.rappi.moviedetail_domain.repository

import com.rappi.core.domain.model.Resource
import com.rappi.moviedetail_domain.model.MovieDetail
import com.rappi.moviedetail_domain.model.MovieVideo

interface MovieDetailRepository {
    suspend fun movieDetail(
        movieID: String, language: String
    ): Resource<MovieDetail>

    suspend fun getMovieVideo(
        movieID: String
    ): Resource<MovieVideo>
}