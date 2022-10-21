package com.rappi.moviedetail_data.mapper

import com.rappi.moviedetail_data.remote.dto.MovieDetailDto
import com.rappi.moviedetail_domain.model.MovieDetail
import com.rappi.moviedetail_domain.model.MovieGenre
import okhttp3.internal.trimSubstring

fun MovieDetailDto.toMovieDetail() = MovieDetail(
    plot = overview,
    genrs = genres
        ?.filterNotNull()
        ?.map { MovieGenre(
            it.id,
            it.name
        )}?: arrayListOf(),
    year = releaseDate.trimSubstring(0, 4),
    originalLanguage = originalLanguage,
    voteAverage = "%.1f".format(voteAverage),
    posterPath = posterPath,
    title = title,
)