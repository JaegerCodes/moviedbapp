package com.rappi.core.data.di

import android.content.Context
import com.rappi.core.BuildConfig
import com.rappi.core.data.remote.moshi.DateMoshiAdapter
import com.rappi.core.data.remote.retrofit.AuthInterceptor
import com.rappi.core.data.remote.retrofit.OfflineInterceptor
import com.rappi.core.data.remote.retrofit.OnlineInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {
    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(DateMoshiAdapter())
        .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        loginInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val cacheSize10MB = 10 * 1024 * 1024
        val timeOutMS = 10L

        return OkHttpClient.Builder()
            .readTimeout(timeOutMS, TimeUnit.SECONDS)
            .connectTimeout(timeOutMS, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, (cacheSize10MB).toLong()))
            .addInterceptor(loginInterceptor)
            .addInterceptor(AuthInterceptor)
            .addInterceptor(OnlineInterceptor)
            .addInterceptor(OfflineInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}
