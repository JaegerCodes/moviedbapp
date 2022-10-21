package com.rappi.moviedetail_presentation.moviedetail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import coil.load
import com.rappi.core.domain.model.Resource
import com.rappi.core.presentation.model.Languages
import com.rappi.core.presentation.ui_extensions.handleApiError
import com.rappi.core_ui.R
import com.rappi.moviedetail_presentation.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@AndroidEntryPoint
class MovieDetailFragment : DialogFragment() {
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private var sharedViewId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(MOVIE_IMAGE_TRANSITION_NAME)?.let {
            sharedViewId = it
        }
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.ui_shared_image)
        postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ViewCompat.setTransitionName(binding.heroImage, sharedViewId)
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = arguments?.getString(POSTER_URL)?: ""
        binding.heroImage.load(imageUrl) {
            crossfade(300)
            listener(
                onError = { _, _ ->
                    binding.heroImage.load(R.drawable.ui_ic_no_image)
                }
            )
        }

        binding.backButton.setOnClickListener {
            dismiss()
        }

        initViewModel()
    }

    private fun initViewModel() {
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> with(binding) {
                    titleMovie.text = it.data.title
                    plotOverview.text = it.data.plot
                    languageText.text = it.data.originalLanguage
                    averageText.text = it.data.voteAverage
                    yearText.text = it.data.year

                    seeTrailerButton.setOnClickListener {

                    }
                }
                is Resource.Failure -> handleApiError(it)
                else -> Unit
            }
        }

        val movieId = arguments?.getString(MOVIE_ID)?: ""
        movieDetailViewModel.getMovieDetail(movieId, Languages.EsES.name)
    }

    override fun getTheme(): Int = android.R.style.Theme_Black_NoTitleBar_Fullscreen

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val MOVIE_IMAGE_TRANSITION_NAME = "movieImageTransitionName"
        const val POSTER_URL = "posterUrl"
        const val MOVIE_ID = "moviId"
    }
}
