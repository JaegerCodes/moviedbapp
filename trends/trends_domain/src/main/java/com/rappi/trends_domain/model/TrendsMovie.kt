package com.rappi.trends_domain.model

import com.rappi.core.domain.model.DMovie

data class TrendsMovie (
    override val adult: Boolean,
    override val backdropPath: String,
    override val genreIDS: List<Long>,
    override val id: Long,
    override val originalLanguage: String,
    override val originalTitle: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String,
    override val releaseDate: String,
    override val title: String,
    override val video: Boolean,
    override val voteAverage: Double,
    override val voteCount: Long
): DMovie
