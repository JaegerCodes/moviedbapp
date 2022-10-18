package com.rappi.trends_presentation.home_trends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.model.Languages
import com.rappi.trends_domain.model.TrendsMoviesDetail
import com.rappi.trends_domain.use_case.GetRemoteTrendsMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendsMoviesViewModel @Inject constructor(
    private val getRemoteTrendsMovies: GetRemoteTrendsMovies
): ViewModel() {
    private val onePage = 1
    private var pages = 1
    private var currentLanguage = Languages.EnUs.name

    private val _upcomingMovies: MutableLiveData<Resource<TrendsMoviesDetail>?> = MutableLiveData()
    val upcomingMovies: LiveData<Resource<TrendsMoviesDetail>?> = _upcomingMovies

    fun getTrendsMovies() {
        pages = onePage
        viewModelScope.launch {
            _upcomingMovies.value = Resource.Loading
            _upcomingMovies.postValue(getRemoteTrendsMovies(pages, currentLanguage))
        }
    }

    fun getNextRecommendationsMovies() {
        pages.plus(onePage)
        viewModelScope.launch {
            _upcomingMovies.value = Resource.Loading
            _upcomingMovies.postValue(getRemoteTrendsMovies(pages, currentLanguage))
        }
    }
}