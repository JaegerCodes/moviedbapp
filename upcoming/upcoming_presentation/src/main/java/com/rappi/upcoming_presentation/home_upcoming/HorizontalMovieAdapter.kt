package com.rappi.upcoming_presentation.home_upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rappi.core.presentation.ui_extensions.PosterSize
import com.rappi.core_ui.databinding.UiImageMovieBinding
import com.rappi.upcoming_domain.model.UpcomingMovie


class HorizontalMovieAdapter(
    private val movies: List<UpcomingMovie>
): RecyclerView.Adapter<HorizontalMovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = UiImageMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            (parent.width * SCREEN_WIDTH_PERCENT).toInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    inner class MovieViewHolder(
        private val binding: UiImageMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(model: UpcomingMovie) = with(binding) {
            movieImage.load(
                PosterSize.Regular.url(model.posterPath)
            ) {
                crossfade(IMAGE_LOAD_CROSSFADE)
                listener(onError = { _, _ ->
                    movieButton.visibility = View.GONE
                })
            }
            movieButton.setOnClickListener {

            }
        }
    }

    companion object {
        private const val IMAGE_LOAD_CROSSFADE = 300
        private const val SCREEN_WIDTH_PERCENT = 0.4
    }
}