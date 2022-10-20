package com.rappi.recommendations_data.mapper

import com.rappi.recommendations_data.remote.dto.Recommendations
import com.rappi.recommendations_domain.model.RecommendationsMovie
import com.rappi.recommendations_domain.model.RecommendationsMoviesDetail
import com.rappi.recommendations_data.remote.dto.RecommendationsDto


fun RecommendationsDto.toTrendsMoviesDetail(): RecommendationsMoviesDetail = RecommendationsMoviesDetail(
    page = page?: 0,
    totalPages = totalPages?: 0,
    movies = results?.mapNotNull { it?.toRecommendationsMovie() }?: arrayListOf()
)

fun Recommendations.toRecommendationsMovie(): RecommendationsMovie = RecommendationsMovie(
    adult = adult?: true,
    backdropPath = backdropPath?: "",
    id = id?: -1,
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