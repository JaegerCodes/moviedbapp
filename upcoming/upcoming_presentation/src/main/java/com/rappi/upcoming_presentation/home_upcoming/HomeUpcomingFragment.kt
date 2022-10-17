package com.rappi.upcoming_presentation.home_upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.upcoming_presentation.databinding.FragmentHomeUpcomingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeUpcomingFragment : Fragment() {
    private val upcomingMoviesViewModel: UpcomingMoviesViewModel by viewModels()
    private var _binding: FragmentHomeUpcomingBinding? = null

    private val binding get() = _binding!!

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
    }

    private fun initViewModels() {
        upcomingMoviesViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.upcomingRecycler.adapter = HorizontalMovieAdapter(it.data.upcoming)
                }
                is Resource.Failure -> handleApiError(it) {

                }
                else -> Unit
            }
        }
        upcomingMoviesViewModel.getUpcomingMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}