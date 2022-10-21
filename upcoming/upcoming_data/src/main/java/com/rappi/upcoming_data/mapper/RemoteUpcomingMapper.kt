package com.rappi.upcoming_data.mapper

import com.rappi.upcoming_data.remote.dto.Upcoming
import com.rappi.upcoming_data.remote.dto.UpcomingDto
import com.rappi.upcoming_domain.model.UpcomingMovie
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail

fun UpcomingDto.toUpcomingMoviesDetail(): UpcomingMoviesDetail = UpcomingMoviesDetail(
    page = page?: 0,
    totalPages = totalPages?: 0,
    movies = results?.mapNotNull { it?.toUpcomingMovie() }?: arrayListOf()
)

fun Upcoming.toUpcomingMovie(): UpcomingMovie = UpcomingMovie(
    adult = adult?: true,
    backdropPath = backdropPath?: "",
    id = id?: "",
    originalLanguage = originalLanguage?: "",
    originalTitle = originalTitle?: "",
    overview = overview?: "",
    popularity = popularity?: .0,
    posterPath = posterPath?: "",
    releaseDate = releaseDate?: "",
    title = title?: "",
    video = video?: false,
    voteAverage = voteAverage?: -1.0,
    voteCount = voteCount?: -1,
    genreIDS = genreIDS?.filterNotNull()?: arrayListOf()
)