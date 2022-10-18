package com.rappi.recommendations_presentation.home_recommendations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.model.Languages
import com.rappi.recommendations_domain.model.RecommendationsMoviesDetail
import com.rappi.recommendations_domain.use_case.GetRemoteRecommendationsMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationsMoviesViewModel @Inject constructor(
    private val getRemoteRecommendationsMovies: GetRemoteRecommendationsMovies
): ViewModel() {
    private val onePage = 1
    private var pages = 1
    private var currentLanguage = Languages.EnUs.name

    private val _upcomingMovies: MutableLiveData<Resource<RecommendationsMoviesDetail>?> = MutableLiveData()
    val upcomingMovies: LiveData<Resource<RecommendationsMoviesDetail>?> = _upcomingMovies

    fun getRecommendationsMovies() {
        pages = onePage
        viewModelScope.launch {
            _upcomingMovies.value = Resource.Loading
            _upcomingMovies.postValue(getRemoteRecommendationsMovies(pages, currentLanguage))
        }
    }

    fun getNextRecommendationsMovies() {
        pages.plus(onePage)
        viewModelScope.launch {
            _upcomingMovies.value = Resource.Loading
            _upcomingMovies.postValue(getRemoteRecommendationsMovies(pages, currentLanguage))
        }
    }
}