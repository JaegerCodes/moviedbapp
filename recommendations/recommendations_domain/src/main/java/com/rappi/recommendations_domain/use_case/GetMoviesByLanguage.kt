package com.rappi.recommendations_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.model.Languages
import com.rappi.recommendations_domain.model.FilteredMovies
import com.rappi.recommendations_domain.repository.RecommendationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesByLanguage @Inject constructor(
    private val repository: RecommendationsRepository
) {
    operator fun invoke(page: Int, originalLanguage: String, listCount: Int = 6): Flow<FilteredMovies> = flow {
        emit(FilteredMovies.Loading)
        when (val response = repository.recommendationsMovies(page, Languages.EsES.name)) {
            is Resource.Failure -> {
                emit(FilteredMovies.Failure(response))
            }
            is Resource.Success -> {
                val filteredMovies = response.data.movies
                    .filter {
                        it.originalLanguage == originalLanguage
                    }
                    .take(listCount)
                var filteredData = response.data
                filteredData = filteredData.copy(
                    movies = filteredMovies
                )
                emit(FilteredMovies.Success(filteredData))
            }
            else -> emit(FilteredMovies.Empty)
        }
    }
}