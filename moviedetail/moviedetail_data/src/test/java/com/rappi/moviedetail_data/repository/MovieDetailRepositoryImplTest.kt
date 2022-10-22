package com.rappi.moviedetail_data.repository

import com.google.common.truth.Truth
import com.rappi.core.domain.model.Resource
import com.rappi.core.domain.network.SafeApiCall
import com.rappi.moviedetail_data.remote.malformedMovieDetailResponse
import com.rappi.moviedetail_data.remote.network.MovieDetailApi
import com.rappi.moviedetail_data.remote.validMovieDetailResponse
import com.rappi.moviedetail_data.remote.validMovieVideos
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MovieDetailRepositoryImplTest {

    private lateinit var repository: MovieDetailRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: MovieDetailApi

    private val apicall = object : SafeApiCall {
        override suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
            return try {
                Resource.Success(apiCall.invoke())
            } catch (e: Exception) {
                Resource.Failure(false, 500)
            }
        }
    }
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(MovieDetailApi::class.java)

        repository = MovieDetailRepositoryImpl(
            api = mockk(relaxed = true)
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get movie detail, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validMovieDetailResponse)
        )
        val result = apicall.safeApiCall {
            api.getMovieDetail("123", "en-US")
        }
        Truth.assertThat(result is Resource.Success).isTrue()
    }

    @Test
    fun `Get movies videos, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(validMovieVideos)
        )
        val result = apicall.safeApiCall {
            api.getMovieVideo("123")
        }
        Truth.assertThat(result is Resource.Success).isTrue()
    }

    @Test
    fun `Get movie detail, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedMovieDetailResponse)
        )
        val result = apicall.safeApiCall {
            api.getMovieDetail("123", "en-US")
        }
        Truth.assertThat(result is Resource.Failure).isTrue()
    }
}