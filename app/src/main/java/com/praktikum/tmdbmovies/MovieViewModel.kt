package com.praktikum.tmdbmovies

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.praktikum.tmdbmovies.local.MovieDao
import com.praktikum.tmdbmovies.local.MovieDatabase
import com.praktikum.tmdbmovies.models.Movie
import com.praktikum.tmdbmovies.models.MovieResponse
import com.praktikum.tmdbmovies.services.MovieApiInterface
import com.praktikum.tmdbmovies.services.MovieApiService
import com.praktikum.tmdbmovies.utils.toMovie
import com.praktikum.tmdbmovies.utils.toMovieEntity
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel(application: Application) : ViewModel() {
    private var movieDao: MovieDao? = null

    private val _movies = MutableLiveData<com.praktikum.tmdbmovies.utils.Resource<List<Movie>>>()
    val movies: LiveData<Resource<List<Movie>>> = _movies

    private val _favoriteMovies = MutableLiveData<com.praktikum.tmdbmovies.utils.Resource<List<Movie>>>()
    val favoriteMovies: LiveData<Resource<List<Movie>>> = _favoriteMovies

    private val _updateFavorite = MutableLiveData<com.praktikum.tmdbmovies.utils.Resource<Unit>>()
    val updateFavorite: LiveData<Resource<Unit>> = _updateFavorite

    init {
        val db = MovieDatabase.getInstance(application)
        movieDao = db.movieDao()

        getALLMovies()

        getFavoriteMovies()
    }

    private fun getALLMovies() {
        viewModelScope.launch {
            _movies.value = com.praktikum.tmdbmovies.utils.Resource.Loading()
            try {
                val movieResponse = MovieApiService.getApiService()
                    .getMovieList().movies
                val movieEntities =
                    movieResponse.map { it.toMovieEntity() }
                movieDao?.insertMovies(movieEntities)

                val result =
                    movieDao?.getMovies()
                val data =
                    result?.map { it.toMovie() }
                data?.let {
                    _movies.value =
                        com.praktikum.tmdbmovies.utils.Resource.Success(data)
                }
            } catch (e : Exception) {
                _movies.value =
                    com.praktikum.tmdbmovies.utils.Resource.Error(e.message.toString())
            }
        }
    }

    fun getFavoriteMovies(){
        viewModelScope.launch {
            _favoriteMovies.value = com.praktikum.tmdbmovies.utils.Resource.Loading()
            try {
                val result =
                    movieDao?.getFavoriteMovies()
                val data =
                    result?.map { it.toMovie() }
            }
        }
    }
}