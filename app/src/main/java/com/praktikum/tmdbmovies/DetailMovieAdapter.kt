package com.praktikum.tmdbmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.praktikum.tmdbmovies.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class DetailMovieAdapter (
    private val movies : List<Movie>
) : RecyclerView.Adapter<DetailMovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie){
            itemView.movie_title.text = movie.title
            itemView.overview.text = movie.overview
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.poster_detail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_detail_movie, parent, false)
        )
    }
    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }
}