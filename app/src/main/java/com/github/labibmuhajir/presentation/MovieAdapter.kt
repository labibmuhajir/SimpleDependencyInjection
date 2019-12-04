package com.github.labibmuhajir.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.labibmuhajir.R
import com.github.labibmuhajir.domain.entity.Movie
import com.github.labibmuhajir.ext.inflate
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var items = mutableSetOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.view_holder_movie))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items.elementAt(position))
    }

    fun addItems(items: List<Movie>) {
        val originalSize = items.size
        this.items.addAll(items)
        notifyItemRangeInserted(originalSize, items.size)
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemView.tvTitle.text = movie.title
        }
    }
}