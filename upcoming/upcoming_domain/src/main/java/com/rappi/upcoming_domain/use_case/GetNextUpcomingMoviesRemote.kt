package com.rappi.upcoming_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail
import com.rappi.upcoming_domain.repository.UpcomingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNextUpcomingMoviesRemote @Inject constructor(
    private val repository: UpcomingRepository
) {
    operator fun invoke(currentPageIndex: Int, totalPages: Long, language: String) = flow {
        emit(Resource.Loading)
        delay(2000)

        val nextPageIndex = currentPageIndex + 1
        if (nextPageIndex <= totalPages) {
            emit(repository.upcomingMovies(nextPageIndex, language))
        } else {
            emit(Resource.Empty)
        }
    }
}