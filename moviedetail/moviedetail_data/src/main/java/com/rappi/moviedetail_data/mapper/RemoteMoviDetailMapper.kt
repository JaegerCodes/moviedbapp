package com.rappi.moviedetail_data.mapper

import com.rappi.moviedetail_data.remote.dto.MovieDetailDto
import com.rappi.moviedetail_domain.model.MovieDetail
import com.rappi.moviedetail_domain.model.MovieGenre

fun MovieDetailDto.toMovieDetail() = MovieDetail(
    plot = overview,
    genrs = genres
        ?.filterNotNull()
        ?.map { MovieGenre(
            it.id,
            it.name
        )}?: arrayListOf(),
    releaseDate = releaseDate,
    originalLanguage = originalLanguage,
    voteAverage = voteAverage,
    posterPath = posterPath,
    title = title,
)