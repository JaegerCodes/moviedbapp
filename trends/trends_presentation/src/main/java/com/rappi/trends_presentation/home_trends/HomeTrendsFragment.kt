package com.rappi.trends_presentation.home_trends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.trends_presentation.databinding.FragmentHomeTrendsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTrendsFragment : Fragment() {

    private val trendsMoviesViewModel: TrendsMoviesViewModel by viewModels()
    private var _binding: FragmentHomeTrendsBinding? = null

    private val binding get() = _binding!!

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
        trendsMoviesViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.upcomingRecycler.adapter = HorizontalMovieAdapter(it.data.trends)
                }
                is Resource.Failure -> handleApiError(it) {
                    // Todo: Load local cache
                }
                else -> Unit
            }
        }
        trendsMoviesViewModel.getUpcomingMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}