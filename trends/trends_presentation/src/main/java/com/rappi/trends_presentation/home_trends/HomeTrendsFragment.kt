package com.rappi.trends_presentation.home_trends

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.rappi.core.domain.model.DMovie
import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.ui_extensions.PosterSize
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.core.presentation.ui_extensions.visible
import com.rappi.moviedetail_presentation.moviedetail.MovieDetailFragment
import com.rappi.trends_presentation.R
import com.rappi.trends_presentation.databinding.FragmentHomeTrendsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeTrendsFragment : Fragment() {
    private val trendsMoviesViewModel: TrendsMoviesViewModel by viewModels()

    private var _binding: FragmentHomeTrendsBinding? = null
    private val binding get() = _binding!!

    private var adapterMovies = HorizontalMovieAdapter()
    private val viewWidthPercent = 0.4

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeTrendsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModels()
    }

    private fun initViewModels() {
        onGetMovies()
    }

    private fun onGetMovies() {
        trendsMoviesViewModel.trendsMovies.observe(viewLifecycleOwner) {
            binding.trendsProgressCenter.visible(it is Resource.Loading, true)
            when (it) {
                is Resource.Success -> {
                    adapterMovies = HorizontalMovieAdapter(
                        getMovieDetail = { movie, posterView ->
                            navigateToMovieDetailPage(posterView, movie)
                        },
                        movies = it.data.movies.toMutableList(),
                        imageWidth = (binding.root.width * viewWidthPercent).toInt()
                    )
                    binding.trendsRecycler.setHasFixedSize(true)
                    binding.trendsRecycler.adapter = adapterMovies
                }
                is Resource.Failure -> handleApiError(it) {}
                else -> Unit
            }
        }
    }

    override fun onResume() {
        super.onResume()
        trendsMoviesViewModel.getTrendsMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
            R.id.trendsMovieDetail,
            args = bundle,
            navOptions = null,
            navigatorExtras = extras
        )
    }
}