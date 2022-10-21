package com.rappi.recommendations_domain.use_case

import android.util.Log
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.model.Languages
import com.rappi.recommendations_domain.model.FilteredMovies
import com.rappi.recommendations_domain.repository.RecommendationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesByYear @Inject constructor(
    private val repository: RecommendationsRepository
) {
    operator fun invoke(page: Int, year: String, listCount: Int = 6): Flow<FilteredMovies> = flow {
        emit(FilteredMovies.Loading)
        when (val response = repository.recommendationsMovies(page, Languages.EsES.name)) {
            is Resource.Failure -> {
                emit(FilteredMovies.Failure(response))
            }
            is Resource.Success -> {
                val filteredMovies = response.data.movies
                    .filter {
                        it.releaseDate.contains(year)
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