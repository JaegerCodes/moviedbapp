package com.rappi.upcoming_domain.model

import com.rappi.core.domain.model.DomainMovie

data class UpcomingMovie (
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
): DomainMovie
