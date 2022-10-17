package com.rappi.recommendations_presentation.home_recommendations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rappi.core.presentation.ui_extensions.PosterSize
import com.rappi.core_ui.databinding.UiGridImageMovieBinding
import com.rappi.recommendations_domain.model.RecommendationsMovie

class GridMovieAdapter(
    private val movies: List<RecommendationsMovie>
): RecyclerView.Adapter<GridMovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = UiGridImageMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    inner class MovieViewHolder(
        private val binding: UiGridImageMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(model: RecommendationsMovie) = with(binding) {
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
    }
}