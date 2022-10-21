package com.rappi.moviedetail_domain.repository

import com.rappi.core.domain.model.Resource
import com.rappi.moviedetail_domain.model.MovieDetail

interface MovieDetailRepository {
    suspend fun movieDetail(
        movieID: String, language: String
    ): Resource<MovieDetail>
}