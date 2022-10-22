package com.rappi.moviedetail_presentation.moviedetail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.core.domain.model.Resource
import com.rappi.moviedetail_domain.model.MovieDetail
import com.rappi.moviedetail_domain.model.MovieVideo
import com.rappi.moviedetail_domain.use_case.MovieDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCases: MovieDetailsUseCases
): ViewModel() {
    private var movieDetailJob: Job? = null
    private var movieMovieJob: Job? = null
    private val _movieDetail: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()
    val movieDetail: LiveData<Resource<MovieDetail>> = _movieDetail

    private val _movieVideo: MutableLiveData<Resource<MovieVideo>> = MutableLiveData()
    val movieVideo: LiveData<Resource<MovieVideo>> = _movieVideo

    fun getMovieDetail(movieId: String, language: String) {
        movieDetailJob?.cancel()
        movieDetailJob = useCases.getDetail(movieId, language)
            .onEach { response ->
                _movieDetail.value = response
            }
            .launchIn(viewModelScope)
    }

    fun getMovieVideo(movieId: String) {
        movieMovieJob?.cancel()
        movieMovieJob = useCases.getVideo(movieId)
            .onEach { response ->
                _movieVideo.value = response
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        movieDetailJob?.cancel()
        movieMovieJob?.cancel()
    }
}