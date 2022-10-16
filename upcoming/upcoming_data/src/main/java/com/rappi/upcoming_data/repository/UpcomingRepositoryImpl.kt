package com.rappi.upcoming_data.repository

import com.rappi.core.BuildConfig
import com.rappi.core.domain.Resource
import com.rappi.upcoming_data.mapper.toUpcomingMoviesDetail
import com.rappi.upcoming_data.remote.network.UpcomingApi
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail
import com.rappi.upcoming_domain.repository.UpcomingRepository
import retrofit2.HttpException

class UpcomingRepositoryImpl(
    private val api: UpcomingApi
): UpcomingRepository {
    override suspend fun upcomingMovies(): Resource<UpcomingMoviesDetail> {
        return try {
            val upcomingMoviesDto = api.getUpcomingMovies()
            Resource.Success(
                upcomingMoviesDto.toUpcomingMoviesDetail()
            )
        } catch(exception: Throwable) {
            if (BuildConfig.DEBUG) {
                exception.printStackTrace()
            }
            when (exception) {
                is HttpException -> Resource.Failure(false, exception.code())
                else -> Resource.Failure(true, null)
            }
        }
    }
}