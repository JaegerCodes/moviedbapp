package com.rappi.core.domain.model

interface DomainMovie {
    val adult: Boolean
    val backdropPath: String
    val genreIDS: List<Long>
    val id: Long
    val originalLanguage: String
    val originalTitle: String
    val overview: String
    val popularity: Double
    val posterPath: String
    val releaseDate: String
    val title: String
    val video: Boolean
    val voteAverage: Double
    val voteCount: Long
}