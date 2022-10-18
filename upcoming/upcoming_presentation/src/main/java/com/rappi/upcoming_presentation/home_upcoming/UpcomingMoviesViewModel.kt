package com.rappi.upcoming_presentation.home_upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rappi.upcoming_domain.use_case.GetRemoteUpcomingMovies
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.model.Languages
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val getRemoteUpcomingMovies: GetRemoteUpcomingMovies
): ViewModel() {
    private val onePage = 1
    private var pages = 1
    private var currentLanguage = Languages.EnUs.name
    private val _upcomingMovies: MutableLiveData<Resource<UpcomingMoviesDetail>?> = MutableLiveData()
    val upcomingMovies: LiveData<Resource<UpcomingMoviesDetail>?> = _upcomingMovies

    fun getUpcomingMovies() {
        pages = onePage
        viewModelScope.launch {
            _upcomingMovies.value = Resource.Loading
            _upcomingMovies.postValue(getRemoteUpcomingMovies(pages, currentLanguage))
        }
    }

    fun getNextRecommendationsMovies() {
        pages.plus(onePage)
        viewModelScope.launch {
            _upcomingMovies.value = Resource.Loading
            _upcomingMovies.postValue(getRemoteUpcomingMovies(pages, currentLanguage))
        }
    }
}