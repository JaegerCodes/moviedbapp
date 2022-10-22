package com.rappi.trends_presentation.home_trends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.domain.model.Resource
import com.rappi.core.app.Languages
import com.rappi.trends_domain.use_case.GetTrendsMoviesRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TrendsMoviesViewModel @Inject constructor(
    private val getTrendsMoviesRemote: GetTrendsMoviesRemote,
): ViewModel() {
    private var trendsMoviesJob: Job? = null
    private var nextTrendsMovies: Job? = null

    private var currentPageIndex = 1
    private var currentLanguage = Languages.EnUs.name

    private val _trendsMovies: MutableLiveData<Resource<DMovieDetail>?> = MutableLiveData()
    val trendsMovies: LiveData<Resource<DMovieDetail>?> = _trendsMovies

    fun getTrendsMovies() {
        trendsMoviesJob?.cancel()
        trendsMoviesJob = getTrendsMoviesRemote(currentPageIndex, currentLanguage)
            .onEach { response ->
                _trendsMovies.value = response
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        trendsMoviesJob?.cancel()
        nextTrendsMovies?.cancel()
    }
}