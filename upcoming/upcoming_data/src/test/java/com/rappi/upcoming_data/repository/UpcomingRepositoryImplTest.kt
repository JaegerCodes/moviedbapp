package com.rappi.upcoming_data.repository

import com.google.common.truth.Truth.assertThat
import com.rappi.core.domain.model.Resource
import com.rappi.core.domain.network.SafeApiCall
import com.rappi.upcoming_data.remote.malformedUpcomingMoviesResponse
import com.rappi.upcoming_data.remote.network.UpcomingApi
import com.rappi.upcoming_data.remote.validUpcomingMoviesResponse
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

class UpcomingRepositoryImplTest {

    private lateinit var repository: UpcomingRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: UpcomingApi

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
            .create(UpcomingApi::class.java)

        repository = UpcomingRepositoryImpl(
            api = mockk(relaxed = true)
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get upcoming movies, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validUpcomingMoviesResponse)
        )
        val result = apicall.safeApiCall {
            api.getUpcomingMovies(3, "en-US")
        }
        assertThat(result is Resource.Success).isTrue()
    }

    @Test
    fun `Get upcoming movies, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedUpcomingMoviesResponse)
        )
        val result = apicall.safeApiCall {
            api.getUpcomingMovies(3, "en-US")
        }
        assertThat(result is Resource.Failure).isTrue()
    }
}