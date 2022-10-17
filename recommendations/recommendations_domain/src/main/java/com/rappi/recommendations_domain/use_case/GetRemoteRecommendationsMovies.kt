package com.rappi.recommendations_domain.use_case

import com.rappi.core.domain.model.Resource
import com.rappi.recommendations_domain.model.RecommendationsMoviesDetail
import com.rappi.recommendations_domain.repository.RecommendationsRepository
import javax.inject.Inject

class GetRemoteRecommendationsMovies @Inject constructor(
    private val repository: RecommendationsRepository
) {
    suspend operator fun invoke(): Resource<RecommendationsMoviesDetail> {
        return repository.recommendationsMovies()
    }
}