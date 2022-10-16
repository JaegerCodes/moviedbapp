package com.rappi.upcoming_presentation.home_upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rappi.upcoming_domain.use_case.GetRemoteUpcomingMovies
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.Resource
import com.rappi.upcoming_domain.model.UpcomingMoviesDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val getRemoteUpcomingMovies: GetRemoteUpcomingMovies
): ViewModel() {

    private val _upcomingMovies: MutableLiveData<Resource<UpcomingMoviesDetail>?> = MutableLiveData()
    val upcomingMovies: LiveData<Resource<UpcomingMoviesDetail>?> = _upcomingMovies

    fun getUpcomingMovies() {
        viewModelScope.launch {
            _upcomingMovies.value = Resource.Loading
            _upcomingMovies.value = getRemoteUpcomingMovies()
        }
    }
}