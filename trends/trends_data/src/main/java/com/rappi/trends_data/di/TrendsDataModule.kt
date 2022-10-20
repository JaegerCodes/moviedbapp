package com.rappi.trends_data.di

import com.rappi.trends_data.remote.network.TrendsApi
import com.rappi.trends_data.repository.TrendsRepositoryImpl
import com.rappi.trends_domain.repository.TrendsRepository
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
object TrendsDataModule {

    @Provides
    @Singleton
    fun provideTrendsApi(
        retrofit: Retrofit
    ): TrendsApi {
        return retrofit.create(TrendsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrendsRepository(
        api: TrendsApi,
    ): TrendsRepository {
        return TrendsRepositoryImpl(
            api = api
        )
    }

}