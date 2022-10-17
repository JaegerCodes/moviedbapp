package com.rappi.trends_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.trends_domain.model.TrendsMoviesDetail
import com.rappi.trends_domain.repository.TrendsRepository
import javax.inject.Inject

class GetRemoteTrendsMovies @Inject constructor(
    private val repository: TrendsRepository
) {
    suspend operator fun invoke(): Resource<TrendsMoviesDetail> {
        return repository.trendsMovies()
    }
}