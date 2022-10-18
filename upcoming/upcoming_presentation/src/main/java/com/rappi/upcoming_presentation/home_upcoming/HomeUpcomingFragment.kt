package com.rappi.upcoming_presentation.home_upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.core.presentation.ui_extensions.visible
import com.rappi.upcoming_presentation.databinding.FragmentHomeUpcomingBinding
import dagger.hilt.android.AndroidEntryPoint


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
        initScrollListener()
        initViewModels()
    }

    private fun initViewModels() {
        upcomingMoviesViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            binding.viewBlocker.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    adapterMovies = HorizontalMovieAdapter(
                        movies = it.data.upcoming,
                        imageWidth = (binding.root.width * viewWidthPercent).toInt()
                    )
                    binding.moviesRecycler.adapter = adapterMovies
                }
                is Resource.Failure -> handleApiError(it) {

                }
                else -> Unit
            }
        }
        upcomingMoviesViewModel.getUpcomingMovies()
    }

    private fun initScrollListener() {
        binding.moviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapterMovies.movies.size - 1) {
                    upcomingMoviesViewModel.getNextUpcomingMovies()
                }
            }
        })
    }

    private fun loadMore() {
        /*rowsArrayList.add(null)
        adapterMovies.notifyItemInserted(rowsArrayList.size() - 1)
        val handler = Handler()
        handler.postDelayed(Runnable {
            rowsArrayList.remove(rowsArrayList.size() - 1)
            val scrollPosition: Int = rowsArrayList.size()
            adapterMovies.notifyItemRemoved(scrollPosition)
            var currentSize = scrollPosition
            val nextLimit = currentSize + 10
            while (currentSize - 1 < nextLimit) {
                rowsArrayList.add("Item $currentSize")
                currentSize++
            }
            adapterMovies.notifyDataSetChanged()
            isLoading = false
        }, 2000)*/
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}