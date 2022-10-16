package com.rappi.upcoming_presentation.home_upcoming

import com.rappi.upcoming_domain.use_case.GetRemoteUpcomingMovies
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val getRemoteUpcomingMovies: GetRemoteUpcomingMovies
): ViewModel() {

    fun getUpcomingMovies() {
        viewModelScope.launch {
            getRemoteUpcomingMovies()
        }
    }
}