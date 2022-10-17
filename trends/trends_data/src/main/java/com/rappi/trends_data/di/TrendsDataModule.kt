package com.rappi.trends_data.di

import com.rappi.core.data.remote.RequestInterceptorTMDB
import com.rappi.trends_data.remote.network.TrendsApi
import com.rappi.trends_data.repository.TrendsRepositoryImpl
import com.rappi.trends_domain.repository.TrendsRepository
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
object TrendsDataModule {

    @Provides
    @Singleton
    fun provideTrendsApi(client: OkHttpClient): TrendsApi {
        return Retrofit.Builder()
            .baseUrl(TrendsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
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