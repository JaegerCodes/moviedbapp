package com.rappi.moviedetail_data.di

import com.rappi.moviedetail_data.remote.network.MovieDetailApi
import com.rappi.moviedetail_data.repository.MovieDetailRepositoryImpl
import com.rappi.moviedetail_domain.repository.MovieDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailDataModule {
    @Provides
    @Singleton
    fun provideTrendsApi(
        retrofit: Retrofit
    ): MovieDetailApi {
        return retrofit.create(MovieDetailApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        api: MovieDetailApi,
    ): MovieDetailRepository {
        return MovieDetailRepositoryImpl(
            api = api
        )
    }
}
