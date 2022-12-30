package com.praktikum.tmdbmovies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.praktikum.tmdbmovies.models.Movie
import com.praktikum.tmdbmovies.models.MovieViewParam
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private val movie = arrayListOf<MovieViewParam>()

    private var callback: ((movie: MovieViewParam) -> Unit)? = null
    private var addToFavorite: ((movie: MovieViewParam) -> Unit)? = null
    private var deleteFromFavorite: ((movie: MovieViewParam) -> Unit)? = null

    private var isFavorite: Boolean = false

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private var callback: ((movie: Movie) -> Unit)? = null
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie){
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            itemView.language.text = movie.language
            itemView.overview.text = movie.overview
            itemView.toggle_favorite.isChecked = movie.isFavorite
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.poster_detail)

            itemView.toggle_favorite.setOnClickListener {
                callback?.invoke(movie)
            }

            itemView.setOnClickListener(View.OnClickListener {
                var id = Intent(itemView.context, DetailMovieActivity::class.java).apply {
                    putExtra("id", movie.id)
                }
                itemView.context.startActivity(id)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }
    override fun getItemCount(): Int = movies.size

    fun submitList(movies: List<MovieViewParam>?) {
        movies?.let {
            this.movie.clear()
            this.movie.addAll(movies)
            notifyDataSetChanged()
        }
    }

    fun setOnClickFavoriteListener(callback: ((movie: MovieViewParam) -> Unit)?) {
        this.callback = callback
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }
}