package com.rappi.recommendations_domain.model

import com.rappi.core.domain.model.DMovie

data class RecommendationsMovie (
    override val adult: Boolean = false,
    override val backdropPath: String = "",
    override val genreIDS: List<Long> = arrayListOf(),
    override val id: Long = 0,
    override val originalLanguage: String = "",
    override val originalTitle: String = "",
    override val overview: String = "",
    override val popularity: Double = .0,
    override val posterPath: String = "",
    override val releaseDate: String = "",
    override val title: String = "",
    override val video: Boolean = false,
    override val voteAverage: Double = .0,
    override val voteCount: Long = 0
): DMovie
