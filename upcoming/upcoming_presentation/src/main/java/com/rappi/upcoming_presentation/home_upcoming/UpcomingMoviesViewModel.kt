package com.rappi.upcoming_presentation.home_upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rappi.upcoming_domain.use_case.GetUpcomingMoviesRemote
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.domain.model.Resource
import com.rappi.core.app.Languages
import com.rappi.upcoming_domain.use_case.GetNextUpcomingMoviesRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val getUpcomingMoviesRemote: GetUpcomingMoviesRemote,
    private val getNextUpcomingMoviesRemote: GetNextUpcomingMoviesRemote
): ViewModel() {
    private var upcomingMoviesJob: Job? = null
    private var nextUpcomingMovies: Job? = null

    private var currentPageIndex = 1
    private var totalPages: Long = 0
    private var currentLanguage = Languages.EnUs.name

    private val _upcomingMovies = MutableStateFlow<Resource<DMovieDetail>>(Resource.Empty)
    val upcomingMovies: StateFlow<Resource<DMovieDetail>> = _upcomingMovies

    private val _nextMovies: MutableLiveData<Resource<DMovieDetail>?> = MutableLiveData()
    val nextMovies: LiveData<Resource<DMovieDetail>?> = _nextMovies

    fun getUpcomingMovies() {
        upcomingMoviesJob?.cancel()
        upcomingMoviesJob = getUpcomingMoviesRemote(currentPageIndex, currentLanguage)
            .onEach { response ->
                _upcomingMovies.value = response
            }
            .launchIn(viewModelScope)
    }

    fun getNextUpcomingMovies() {
        nextUpcomingMovies?.cancel()
        nextUpcomingMovies = getNextUpcomingMoviesRemote(currentPageIndex, totalPages, currentLanguage)
            .onEach { response ->
                _nextMovies.value = response
            }
            .launchIn(viewModelScope)
    }

    fun updateMoviesPagesData(pagesData: DMovieDetail) {
        currentPageIndex = pagesData.page
        totalPages = pagesData.totalPages
    }

    override fun onCleared() {
        super.onCleared()
        upcomingMoviesJob?.cancel()
        nextUpcomingMovies?.cancel()
    }
}