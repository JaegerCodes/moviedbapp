package com.rappi.upcoming_domain.repository

interface UpcomingMoviesRepository {
    suspend fun upcomingMovies(): Result<List<Nothing>>
}