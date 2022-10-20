package com.rappi.trends_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.trends_domain.repository.TrendsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTrendsMoviesRemote @Inject constructor(
    private val repository: TrendsRepository
) {
    operator fun invoke(page: Int, language: String) = flow {
        emit(Resource.Loading)
        emit(repository.trendsMovies(page, language))
        emit(Resource.Empty)
    }
}
