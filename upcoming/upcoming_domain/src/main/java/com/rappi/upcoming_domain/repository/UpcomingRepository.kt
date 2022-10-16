package com.rappi.upcoming_domain.repository

interface UpcomingRepository {
    suspend fun upcomingMovies(): Result<List<Nothing>>
}