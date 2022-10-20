package com.rappi.upcoming_presentation.home_upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rappi.core.domain.model.DMovieDetail
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.core.presentation.ui_extensions.visible
import com.rappi.upcoming_presentation.databinding.FragmentHomeUpcomingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeUpcomingFragment : Fragment() {
    private val upcomingMoviesViewModel: UpcomingMoviesViewModel by viewModels()
    private var _binding: FragmentHomeUpcomingBinding? = null
    private val binding get() = _binding!!

    private var adapterMovies = HorizontalMovieAdapter()
    private val viewWidthPercent = 0.4

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModels()
        upcomingMoviesViewModel.getUpcomingMovies()
    }

    private fun initViewModels() {
        onGetUpcomingMovies()
        onGetNextMovies()
    }

    private fun onGetUpcomingMovies() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            upcomingMoviesViewModel.upcomingMovies.collect {
                binding.upcomingProgressCenter.visible(it is Resource.Loading, true)
                when (it) {
                    is Resource.Success -> onGetMoviesFirstResponse(it.data)
                    is Resource.Failure -> handleApiError(it) {}
                    else -> Unit
                }
            }
        }
    }

    private fun onGetNextMovies() {
        upcomingMoviesViewModel.nextMovies.observe(viewLifecycleOwner) {
            binding.upcomingProgressRight.visible(it is Resource.Loading, true)
            when (it) {
                is Resource.Success -> {
                    adapterMovies.insertMoviesOnRequestNextMoviesEnds(it.data.movies)
                    upcomingMoviesViewModel.updateMoviesPagesData(it.data)
                }
                is Resource.Failure -> handleApiError(it) {}
                else -> Unit
            }
        }
    }

    private fun onGetMoviesFirstResponse(upcomingMoviesDetail: DMovieDetail) {
        upcomingMoviesViewModel.updateMoviesPagesData(upcomingMoviesDetail)
        adapterMovies = HorizontalMovieAdapter(
            scrollToPosition = { position ->
                scrollToItemPosition(position)
            },
            movies = upcomingMoviesDetail.movies.toMutableList(),
            imageWidth = (binding.root.width * viewWidthPercent).toInt()
        )
        binding.upcomingRecycler.adapter = adapterMovies
        initScrollListener()
    }

    private fun scrollToItemPosition(itemPosition: Int) {
        binding.upcomingRecycler.scrollToPosition(itemPosition)
    }


    private fun initScrollListener() {
        binding.upcomingRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapterMovies.movies.size - 1) {
                    upcomingMoviesViewModel.getNextUpcomingMovies()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}