package com.rappi.recommendations_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.recommendations_domain.model.FilteredMovies
import com.rappi.recommendations_domain.model.RecommendationsMoviesDetail
import com.rappi.recommendations_domain.repository.RecommendationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecommendationsMoviesRemote @Inject constructor(
    private val repository: RecommendationsRepository
) {
    operator fun invoke(page: Int, language: String, listCount: Int = 6): Flow<FilteredMovies> = flow {
        emit(FilteredMovies.Loading)
        when (val response = repository.recommendationsMovies(page, language)) {
            is Resource.Failure -> {
                emit(FilteredMovies.Failure(response))
            }
            is Resource.Success -> {
                var filteredData = response.data
                filteredData = filteredData.copy(
                    movies = response.data.movies.take(listCount)
                )
                emit(FilteredMovies.Success(filteredData))
            }
            else -> emit(FilteredMovies.Empty)
        }
    }
}

