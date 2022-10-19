package com.rappi.core.domain.model

interface DMovieDetail {
    val page: Int
    val movies: List<DMovie>
    val totalPages: Long
}
