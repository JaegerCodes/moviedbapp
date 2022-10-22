package com.rappi.moviedetail_presentation.moviedetail

import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.rappi.core_ui.R
import com.rappi.moviedetail_domain.model.MovieGenre


class MovieGenrsAdapter(
    private val genrs: List<MovieGenre>
): RecyclerView.Adapter<MovieGenrsAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = TextView(parent.context)
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(genrs[position], position)
    }

    inner class MovieViewHolder(
        private val binding: TextView
    ): RecyclerView.ViewHolder(binding) {
        fun bindItem(model: MovieGenre, position: Int) {
            binding.setTextAppearance(R.style.Theme_CoreUI_Text125H6_Light)
            binding.gravity = Gravity.CENTER
            var textValue = model.name
            val dot = Html.fromHtml("•", HtmlCompat.FROM_HTML_MODE_LEGACY)


            if (position > 0 && position < genrs.size) {
                textValue = " $dot  $textValue "
            }
            binding.text = textValue
        }
    }

    override fun getItemCount() = genrs.size
}