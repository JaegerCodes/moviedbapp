package com.rappi.moviedetail_domain.model

fun movieDetailMock() = MovieDetail(
    plot = "A plot",
    genrs = arrayListOf(MovieGenre(1, "es")),
    year = "2020",
    originalLanguage = "es",
    voteAverage = "8.5",
    posterPath = "http://movie.com",
    title = "Movie Title "
)