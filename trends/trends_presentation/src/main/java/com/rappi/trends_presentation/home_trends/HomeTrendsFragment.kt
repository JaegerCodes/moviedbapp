package com.rappi.trends_presentation.home_trends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        trendsMoviesViewModel.getTrendsMovies()
    }

    private fun initViewModels() {
        onGetMovies()
        onGetNextMovies()
    }

    private fun onGetMovies() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            trendsMoviesViewModel.trendsMovies.collect {
                binding.trendsProgressCenter.visible(it is Resource.Loading, true)
                when (it) {
                    is Resource.Success -> onGetMoviesFirstResponse(it.data)
                    is Resource.Failure -> handleApiError(it) {}
                    else -> Unit
                }
            }
        }
    }

    private fun onGetNextMovies() {
        trendsMoviesViewModel.nextMovies.observe(viewLifecycleOwner) {
            binding.trendsProgressRight.visible(it is Resource.Loading, true)
            when (it) {
                is Resource.Success -> {
                    adapterMovies.insertMoviesOnRequestNextMoviesEnds(it.data.movies)
                    trendsMoviesViewModel.updateMoviesPagesData(it.data)
                }
                is Resource.Failure -> handleApiError(it) {}
                else -> Unit
            }
        }
    }

    private fun onGetMoviesFirstResponse(upcomingMoviesDetail: DMovieDetail) {
        trendsMoviesViewModel.updateMoviesPagesData(upcomingMoviesDetail)
        adapterMovies = HorizontalMovieAdapter(
            scrollToPosition = { position ->
                scrollToItemPosition(position)
            },
            movies = upcomingMoviesDetail.movies.toMutableList(),
            imageWidth = (binding.root.width * viewWidthPercent).toInt()
        )
        binding.trendsRecycler.adapter = adapterMovies
        initScrollListener()
    }

    private fun scrollToItemPosition(itemPosition: Int) {
        binding.trendsRecycler.scrollToPosition(itemPosition)
    }


    private fun initScrollListener() {
        binding.trendsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapterMovies.movies.size - 1) {
                    trendsMoviesViewModel.getNextMovies()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}