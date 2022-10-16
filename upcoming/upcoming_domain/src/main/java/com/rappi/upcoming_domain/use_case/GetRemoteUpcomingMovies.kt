package com.rappi.upcoming_domain.use_case

import com.rappi.core.domain.Resource
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail
import com.rappi.upcoming_domain.repository.UpcomingRepository
import javax.inject.Inject

class GetRemoteUpcomingMovies @Inject constructor(
    private val repository: UpcomingRepository
) {
    suspend operator fun invoke(): Resource<UpcomingMoviesDetail> {
        return repository.upcomingMovies()
    }
}