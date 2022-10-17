package com.rappi.recommendations_presentation.home_recommendations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.recommendations_presentation.databinding.FragmentHomeRecommendationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeRecommendationsFragment : Fragment() {

    private val upcomingMoviesViewModel: RecommendationsMoviesViewModel by viewModels()
    private var _binding: FragmentHomeRecommendationsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeRecommendationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModels()
    }

    private fun initViewModels() {
        upcomingMoviesViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.moviesRecycler.adapter = GridMovieAdapter(it.data.recommendations)
                }
                is Resource.Failure -> handleApiError(it) {

                }
                else -> Unit
            }
        }
        upcomingMoviesViewModel.getRecommendationsMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}