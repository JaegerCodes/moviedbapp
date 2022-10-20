package com.rappi.recommendations_domain.model

import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.domain.model.Resource

sealed class FilteredMovies {
    object Loading: FilteredMovies()
    object Empty: FilteredMovies()
    data class Failure(val ApiFail: Resource.Failure): FilteredMovies()
    data class Success(val detail: DMovieDetail): FilteredMovies()
}