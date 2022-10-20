package com.rappi.upcoming_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail
import com.rappi.upcoming_domain.repository.UpcomingRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUpcomingMoviesRemote @Inject constructor(
    private val repository: UpcomingRepository
) {
    operator fun invoke(page: Int, language: String) = flow {
        emit(Resource.Loading)
        emit(repository.upcomingMovies(page, language))
    }
}