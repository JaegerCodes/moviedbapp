package com.rappi.recommendations_presentation.home_recommendations

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rappi.core.domain.model.DMovie
import com.rappi.core.presentation.ui_extensions.PosterSize
import com.rappi.core_ui.R
import com.rappi.core_ui.databinding.UiGridImageMovieBinding

class GridMovieAdapter(
    val movies: MutableList<DMovie> = mutableListOf(),
    val getMovieDetail: (movie: DMovie, posterView: ImageView) -> Unit = { _, _ ->},
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
        fun bindItem(model: DMovie) = with(binding) {
            movieImage.load(
                PosterSize.Large.url(model.posterPath)
            ) {
                crossfade(300)
                movieImage.load(R.drawable.ui_ic_no_image)
            }
            ViewCompat.setTransitionName(movieImage, "recommendation${model.id}")
            movieButton.setOnClickListener {
                getMovieDetail.invoke(model, movieImage)
            }
        }
    }
}