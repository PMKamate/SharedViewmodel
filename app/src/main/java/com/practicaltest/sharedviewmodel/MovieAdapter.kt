package com.practicaltest.sharedviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MovieAdapter(var movieList: List<Movie>, var onItemCLickListener: OnItemCLickListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView
        private val ratingTextView: TextView
        fun bind(movie: Movie) {
            nameTextView.text = movie.name
            ratingTextView.text = movie.rating.toString()
        }

        init {
            nameTextView = itemView.findViewById(R.id.nameTextView)
            ratingTextView = itemView.findViewById(R.id.ratingTextView)
            itemView.setOnClickListener { onItemCLickListener.onItemClick(adapterPosition) }
        }
    }

    interface OnItemCLickListener {
        fun onItemClick(position: Int)
    }
}
