package com.rappi.recommendations_presentation.home_recommendations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.presentation.model.Languages
import com.rappi.recommendations_domain.model.FilteredMovies
import com.rappi.recommendations_domain.use_case.GetRecommendationsMoviesWithFilterRemote
import com.rappi.recommendations_domain.use_case.GetRecommendationsMoviesRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecommendationsMoviesViewModel @Inject constructor(
    private val getRecommendationsMoviesRemote: GetRecommendationsMoviesRemote,
    private val getMoviesWithFilterRemote: GetRecommendationsMoviesWithFilterRemote
): ViewModel() {
    private var recommendationsMoviesJob: Job? = null
    private var nextRecommendationsMovies: Job? = null

    private var currentPageIndex = 1
    private var totalPages: Long = 0
    private var currentLanguage = Languages.EnUs.name
    private var releaseDate = ""

    private val _recommendationsMovies: MutableLiveData<FilteredMovies> = MutableLiveData()
    val recommendationsMovies: LiveData<FilteredMovies> = _recommendationsMovies

    private val _filteredMovies: MutableLiveData<FilteredMovies> = MutableLiveData()
    val filteredMovies: LiveData<FilteredMovies> = _filteredMovies

    fun getRecommendationsMovies() {
        recommendationsMoviesJob?.cancel()
        recommendationsMoviesJob = getRecommendationsMoviesRemote(currentPageIndex, currentLanguage)
            .onEach { response ->
                _recommendationsMovies.postValue(response)
            }
            .launchIn(viewModelScope)
    }

    fun getMoviesWithFilter(selectedLanguage: String) {
        currentLanguage = selectedLanguage

        nextRecommendationsMovies?.cancel()
        nextRecommendationsMovies = getMoviesWithFilterRemote(currentPageIndex, currentLanguage, )
            .onEach { response ->
                _filteredMovies.postValue(response)
            }
            .launchIn(viewModelScope)
    }

    fun updateMoviesPagesData(pagesData: DMovieDetail) {
        currentPageIndex = pagesData.page
        totalPages = pagesData.totalPages
    }

    override fun onCleared() {
        super.onCleared()
        recommendationsMoviesJob?.cancel()
        nextRecommendationsMovies?.cancel()
    }
}