package com.rappi.trends_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.trends_domain.repository.TrendsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNextTrendsMoviesRemote @Inject constructor(
    private val repository: TrendsRepository
) {
    operator fun invoke(currentPageIndex: Int, totalPages: Long, language: String) = flow {
        val nextPageIndex = currentPageIndex + 1

        if (nextPageIndex <= totalPages) {
            emit(Resource.Loading)
            delay(2000)
            emit(repository.trendsMovies(nextPageIndex, language))
        } else {
            emit(Resource.Empty)
        }
    }
}
