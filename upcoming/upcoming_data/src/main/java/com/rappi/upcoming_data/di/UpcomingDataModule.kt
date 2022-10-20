package com.rappi.upcoming_data.di

import com.rappi.upcoming_data.remote.network.UpcomingApi
import com.rappi.upcoming_data.repository.UpcomingRepositoryImpl
import com.rappi.upcoming_domain.repository.UpcomingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UpcomingDataModule {

    @Provides
    @Singleton
    fun provideUpcomingApi(
        retrofit: Retrofit
    ): UpcomingApi {
        return retrofit.create(UpcomingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUpcomingRepository(
        api: UpcomingApi,
    ): UpcomingRepository {
        return UpcomingRepositoryImpl(
            api = api
        )
    }
}