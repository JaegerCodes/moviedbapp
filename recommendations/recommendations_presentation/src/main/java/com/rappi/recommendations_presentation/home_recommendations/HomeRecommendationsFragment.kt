package com.rappi.recommendations_presentation.home_recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.rappi.core.domain.model.DMovie
import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.presentation.ui_extensions.PosterSize
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.moviedetail_presentation.moviedetail.MovieDetailFragment
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
    private var checkedChip: Chip? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.filterChips.setOnCheckedStateChangeListener { _, checkedIds ->
            checkedIds.map { chipId ->
                checkedChip = requireView().findViewById(chipId)
                when (chipId) {
                    R.id.filter_language -> {
                        if (checkedChip?.isChecked == true) {
                            moviesViewModel.getMoviesByOriginalLanguage("en")
                            return@map
                        }
                    }
                    R.id.filter_release_date -> {
                        if (checkedChip?.isChecked == true) {
                            moviesViewModel.getMoviesByYear("2020")
                            return@map
                        }
                    }
                }
            }
        }
        initViewModels()
        moviesViewModel.getRecommendationsMovies()
    }

    private fun initViewModels() {
        onGetUpcomingMovies()
    }

    private fun onGetUpcomingMovies() {
        moviesViewModel.recommendationsMovies.observe(viewLifecycleOwner) { response ->

            //parentViewVisible(response is FilteredMovies.Loading && adapterMovies.itemCount > 0)
            when (response) {
                is FilteredMovies.Success -> (view?.parent as? ViewGroup)?.doOnPreDraw {
                    onGetUpcomingMoviesFromApi(response.detail)
                    startPostponedEnterTransition()
                }
                is FilteredMovies.Failure -> handleApiError(response.ApiFail) {}
                else -> Unit
            }
        }

        moviesViewModel.filteredMovies.observe(viewLifecycleOwner) { response ->
            //parentViewVisible(response is FilteredMovies.Loading && adapterMovies.itemCount > 0)
            when (response) {
                is FilteredMovies.Success -> {
                    onGetUpcomingMoviesFromApi(response.detail)
                }
                is FilteredMovies.Failure -> handleApiError(response.ApiFail) {}
                else -> Unit
            }
        }

    }

    private fun onGetUpcomingMoviesFromApi(upcomingMoviesDetail: DMovieDetail) {
        moviesViewModel.updateMoviesPagesData(upcomingMoviesDetail)
        adapterMovies = GridMovieAdapter(
            getMovieDetail = { movie, posterView ->
                navigateToMovieDetailPage(posterView, movie)
            },
            movies = upcomingMoviesDetail.movies.toMutableList(),
        )
        binding.moviesRecycler.adapter = adapterMovies
    }

    private fun navigateToMovieDetailPage(
        posterView: ImageView,
        movie: DMovie
    ) {
        val bundle = bundleOf(
            MovieDetailFragment.MOVIE_IMAGE_TRANSITION_NAME to posterView.transitionName,
            MovieDetailFragment.POSTER_URL to PosterSize.Large.url(movie.posterPath),
            MovieDetailFragment.MOVIE_ID to movie.id,
        )
        val extras = FragmentNavigatorExtras(
            posterView to posterView.transitionName
        )
        findNavController().navigate(
            R.id.recommendationsMovieDetail,
            args = bundle,
            navOptions = null,
            navigatorExtras = extras
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}