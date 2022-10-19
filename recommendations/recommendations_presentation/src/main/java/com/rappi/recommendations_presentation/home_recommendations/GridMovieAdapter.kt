package com.rappi.recommendations_presentation.home_recommendations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rappi.core.domain.model.DMovie
import com.rappi.core.presentation.ui_extensions.PosterSize
import com.rappi.core_ui.databinding.UiGridImageMovieBinding
import com.rappi.recommendations_domain.model.RecommendationsMovie

class GridMovieAdapter(
    val movies: MutableList<DMovie> = mutableListOf(),
    val scrollToPosition: (scrollPosition: Int) -> Unit = {},
): RecyclerView.Adapter<GridMovieAdapter.MovieViewHolder>() {

    private val imageLoadCrossfade = 300

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
        fun bindItem(model: DMovie) = with(binding) {
            movieImage.load(
                PosterSize.Large.url(model.posterPath)
            ) {
                crossfade(imageLoadCrossfade)
                listener(onError = { _, _ ->
                    movieImage.load(com.rappi.core_ui.R.drawable.ui_ic_no_image)
                })
            }
            movieButton.setOnClickListener {
            }
        }
    }
}
