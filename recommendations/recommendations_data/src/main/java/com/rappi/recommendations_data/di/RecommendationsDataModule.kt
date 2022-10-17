package com.rappi.recommendations_data.di

import com.rappi.recommendations_data.remote.network.RecommendationsApi
import com.rappi.recommendations_data.repository.RecommendationsRepositoryImpl
import com.rappi.recommendations_domain.repository.RecommendationsRepository
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
object RecommendationsDataModule {

    @Provides
    @Singleton
    fun provideRecommendationsApi(client: OkHttpClient): RecommendationsApi {
        return Retrofit.Builder()
            .baseUrl(RecommendationsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRecommendationsRepository(
        api: RecommendationsApi,
    ): RecommendationsRepository {
        return RecommendationsRepositoryImpl(
            api = api
        )
    }

}