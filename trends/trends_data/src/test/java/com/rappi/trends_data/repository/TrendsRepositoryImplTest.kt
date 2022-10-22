package com.rappi.trends_data.repository

import com.google.common.truth.Truth
import com.rappi.core.domain.model.Resource
import com.rappi.core.domain.network.SafeApiCall
import com.rappi.trends_data.remote.malformedTrendsMoviesResponse
import com.rappi.trends_data.remote.network.TrendsApi
import com.rappi.trends_data.remote.validTrendsMoviesResponse
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

class TrendsRepositoryImplTest {

    private lateinit var repository: TrendsRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: TrendsApi

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
            .create(TrendsApi::class.java)

        repository = TrendsRepositoryImpl(
            api = mockk(relaxed = true)
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get trends movies, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validTrendsMoviesResponse)
        )
        val result = apicall.safeApiCall {
            api.getTrendsMovies(3, "en-US")
        }
        Truth.assertThat(result is Resource.Success).isTrue()
    }

    @Test
    fun `Get trends movies, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedTrendsMoviesResponse)
        )
        val result = apicall.safeApiCall {
            api.getTrendsMovies(3, "en-US")
        }
        Truth.assertThat(result is Resource.Failure).isTrue()
    }
}