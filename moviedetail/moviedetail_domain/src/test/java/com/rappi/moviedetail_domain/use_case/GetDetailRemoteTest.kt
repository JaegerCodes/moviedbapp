package com.rappi.moviedetail_domain.use_case

import com.google.common.truth.Truth
import com.rappi.core.domain.model.Resource
import com.rappi.moviedetail_domain.model.movieDetailMock
import com.rappi.moviedetail_domain.repository.MovieDetailRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetDetailRemoteTest {

    private lateinit var getDetailRemote: GetDetailRemote
    private lateinit var repository: MovieDetailRepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        getDetailRemote = GetDetailRemote(repository)
    }

    @Test
    fun `Get movie detail properly`() = runBlocking {

        val useCase = getDetailRemote("505642","en-US")

        coEvery {
            repository.movieDetail("505642", "en-US")
        } returns Resource.Success(
            movieDetailMock()
        )
        useCase.collect {
            if (it is Resource.Success) {
                Truth.assertThat(it.data).isEqualTo(movieDetailMock())
            }
        }
    }

    @After
    fun shutdown() {
        clearAllMocks()
    }
}