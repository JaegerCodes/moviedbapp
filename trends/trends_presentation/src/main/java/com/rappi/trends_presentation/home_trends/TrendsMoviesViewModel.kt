package com.rappi.trends_presentation.home_trends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.model.Languages
import com.rappi.trends_domain.use_case.GetNextTrendsMoviesRemote
import com.rappi.trends_domain.use_case.GetTrendsMoviesRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TrendsMoviesViewModel @Inject constructor(
    private val getTrendsMoviesRemote: GetTrendsMoviesRemote,
    private val getNextTrendsMoviesRemote: GetNextTrendsMoviesRemote
): ViewModel() {
    private var trendsMoviesJob: Job? = null
    private var nextTrendsMovies: Job? = null

    private var currentPageIndex = 1
    private var totalPages: Long = 0
    private var currentLanguage = Languages.EnUs.name

    private val _trendsMovies = MutableStateFlow<Resource<DMovieDetail>>(Resource.Empty)
    val trendsMovies: StateFlow<Resource<DMovieDetail>> = _trendsMovies

    private val _nextMovies: MutableLiveData<Resource<DMovieDetail>?> = MutableLiveData()
    val nextMovies: LiveData<Resource<DMovieDetail>?> = _nextMovies

    fun getTrendsMovies() {
        trendsMoviesJob?.cancel()
        trendsMoviesJob = getTrendsMoviesRemote(currentPageIndex, currentLanguage)
            .onEach { response ->
                _trendsMovies.value = response
            }
            .launchIn(viewModelScope)
    }

    fun getNextMovies() {
        nextTrendsMovies?.cancel()
        nextTrendsMovies = getNextTrendsMoviesRemote(currentPageIndex, totalPages, currentLanguage)
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
        trendsMoviesJob?.cancel()
        nextTrendsMovies?.cancel()
    }
}