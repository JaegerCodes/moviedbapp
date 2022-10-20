package com.rappi.recommendations_presentation.home_recommendations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.core.presentation.ui_extensions.visible
import com.rappi.core_ui.parentViewVisible
import com.rappi.recommendations_domain.model.FilteredMovies
import com.rappi.recommendations_presentation.R
import com.rappi.recommendations_presentation.databinding.FragmentHomeRecommendationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeRecommendationsFragment : Fragment() {
    private val moviesViewModel: RecommendationsMoviesViewModel by viewModels()

    private var _binding: FragmentHomeRecommendationsBinding? = null
    private val binding get() = _binding!!

    private var adapterMovies = GridMovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeRecommendationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.filterChips.setOnCheckedStateChangeListener { group, checkedIds ->  }
        initViewModels()
        moviesViewModel.getRecommendationsMovies()
    }

    private fun initViewModels() {
        onGetUpcomingMovies()
    }

    private fun onGetUpcomingMovies() {
        moviesViewModel.recommendationsMovies.observe(viewLifecycleOwner) {

            parentViewVisible(it is FilteredMovies.Loading && adapterMovies.itemCount > 0)
            when (it) {
                is FilteredMovies.Success -> onGetUpcomingMoviesFirstResponse(it.detail)
                is FilteredMovies.Failure -> handleApiError(it.ApiFail) {}
                else -> Unit
            }
        }
    }

    private fun onGetUpcomingMoviesFirstResponse(upcomingMoviesDetail: DMovieDetail) {
        moviesViewModel.updateMoviesPagesData(upcomingMoviesDetail)
        adapterMovies = GridMovieAdapter(
            getMovieDetail = {
                findNavController().navigate(R.id.recommendationsMovieDetail)
            },
            movies = upcomingMoviesDetail.movies.toMutableList(),
        )
        binding.moviesRecycler.adapter = adapterMovies
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}