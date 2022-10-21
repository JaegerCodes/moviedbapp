package com.rappi.upcoming_presentation.home_upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rappi.core.domain.model.DMovie
import com.rappi.core.presentation.ui_extensions.PosterSize
import com.rappi.core_ui.R
import com.rappi.core_ui.databinding.UiImageMovieBinding

class HorizontalMovieAdapter(
    val movies: MutableList<DMovie> = mutableListOf(),
    val scrollToPosition: (scrollPosition: Int) -> Unit = {},
    val getMovieDetail: (movie: DMovie, posterView: ImageView) -> Unit = { _, _ ->},
    private val imageWidth: Int = 0
): RecyclerView.Adapter<HorizontalMovieAdapter.MovieViewHolder>() {

    private val imageLoadCrossfade = 300

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = UiImageMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            imageWidth,
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
        fun bindItem(model: DMovie) = with(binding) {
            movieImage.load(
                PosterSize.Large.url(model.posterPath)
            ) {
                crossfade(300)
                placeholder(R.drawable.ui_ic_no_image)
                listener(
                    onError = { _, _ ->
                        movieImage.load(R.drawable.ui_ic_no_image)
                    }
                )
            }
            ViewCompat.setTransitionName(movieImage, "trends${model.id}")
            movieButton.setOnClickListener {
                getMovieDetail.invoke(model, movieImage)
            }
        }
    }

    fun insertMoviesOnRequestNextMoviesEnds(nextMovies: List<DMovie>) {
        if (nextMovies.isNotEmpty()) {
            val positionStart = movies.size
            val itemCount = movies.size + nextMovies.size
            movies.addAll(nextMovies)
            notifyItemRangeChanged(positionStart, itemCount)
            scrollToPosition.invoke(positionStart)
        }
    }
}