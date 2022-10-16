package com.rappi.upcoming_data.di

import com.rappi.upcoming_data.remote.network.RequestInterceptor
import com.rappi.upcoming_data.remote.network.UpcomingApi
import com.rappi.upcoming_data.repository.UpcomingRepositoryImpl
import com.rappi.upcoming_domain.repository.UpcomingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UpcomingDataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideUpcomingApi(client: OkHttpClient): UpcomingApi {
        return Retrofit.Builder()
            .baseUrl(UpcomingApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
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