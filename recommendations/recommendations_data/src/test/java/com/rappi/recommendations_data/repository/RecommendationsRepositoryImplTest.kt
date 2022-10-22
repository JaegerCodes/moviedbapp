package com.rappi.recommendations_data.repository

import com.google.common.truth.Truth
import com.rappi.core.domain.model.Resource
import com.rappi.core.domain.network.SafeApiCall
import com.rappi.recommendations_data.remote.malformedRecommendationsMoviesResponse
import com.rappi.recommendations_data.remote.network.RecommendationsApi
import com.rappi.recommendations_data.remote.validRecommendationsMoviesResponse
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

class RecommendationsRepositoryImplTest {

    private lateinit var repository: RecommendationsRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: RecommendationsApi

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
            .create(RecommendationsApi::class.java)

        repository = RecommendationsRepositoryImpl(
            api = mockk(relaxed = true)
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get recommendations movies, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validRecommendationsMoviesResponse)
        )
        val result = apicall.safeApiCall {
            api.getRecommendationsMovies(3, "en-US")
        }
        Truth.assertThat(result is Resource.Success).isTrue()
    }

    @Test
    fun `Get recommendations movies, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedRecommendationsMoviesResponse)
        )
        val result = apicall.safeApiCall {
            api.getRecommendationsMovies(3, "en-US")
        }
        Truth.assertThat(result is Resource.Failure).isTrue()
    }
}