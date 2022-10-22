package com.rappi.moviedetail_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.moviedetail_domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailRemote @Inject constructor(
    private val repository: MovieDetailRepository
) {
    operator fun invoke(movieID: String, language: String) = flow {
        emit(Resource.Loading)
        emit(repository.movieDetail(movieID, language))
    }
}

