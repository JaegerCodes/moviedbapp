package com.rappi.moviedetail_presentation.moviedetail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.Resource
import com.rappi.moviedetail_domain.model.MovieDetail
import com.rappi.moviedetail_domain.use_case.GetMovieDetailRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailRemote: GetMovieDetailRemote
): ViewModel() {
    private var movieDetailJob: Job? = null

    private val _movieDetail: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()
    val movieDetail: LiveData<Resource<MovieDetail>> = _movieDetail

    fun getMovieDetail(movieId: String, language: String) {
        movieDetailJob?.cancel()
        movieDetailJob = getMovieDetailRemote(movieId, language)
            .onEach { response ->
                _movieDetail.value = response
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        movieDetailJob?.cancel()
    }
}