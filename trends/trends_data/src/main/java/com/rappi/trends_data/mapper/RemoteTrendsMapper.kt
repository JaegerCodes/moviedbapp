package com.rappi.trends_data.mapper

import com.rappi.trends_data.remote.dto.Trends
import com.rappi.trends_data.remote.dto.TrendsDto
import com.rappi.trends_domain.model.TrendsMovie
import com.rappi.trends_domain.model.TrendsMoviesDetail

fun TrendsDto.toTrendsMoviesDetail(): TrendsMoviesDetail = TrendsMoviesDetail(
    page = page?: 0,
    totalPages = totalPages?: 0,
    movies = results?.mapNotNull { it?.toTrendsMovie() }?: arrayListOf()
)

fun Trends.toTrendsMovie(): TrendsMovie = TrendsMovie(
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