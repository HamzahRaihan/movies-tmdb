package com.praktikum.tmdbmovies

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praktikum.tmdbmovies.local.MovieDao
import com.praktikum.tmdbmovies.local.MovieDatabase
import com.praktikum.tmdbmovies.models.MovieViewParam
import com.praktikum.tmdbmovies.services.MovieApiService
import com.praktikum.tmdbmovies.utils.toMovieViewParam
import com.praktikum.tmdbmovies.utils.toMovieEntity
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel(application: Application) : ViewModel() {

    private var movieDao: MovieDao? = null

    private val _movies = MutableLiveData<com.praktikum.tmdbmovies.utils.Resource<List<MovieViewParam>>>()
    val movies: LiveData<com.praktikum.tmdbmovies.utils.Resource<List<MovieViewParam>>> = _movies

    private val _favoriteMovies = MutableLiveData<com.praktikum.tmdbmovies.utils.Resource<List<MovieViewParam>>>()
    val favoriteMovies: LiveData<com.praktikum.tmdbmovies.utils.Resource<List<MovieViewParam>>> = _favoriteMovies

    private val _updateFavorite = MutableLiveData<com.praktikum.tmdbmovies.utils.Resource<Unit>>()
    val updateFavorite: LiveData<com.praktikum.tmdbmovies.utils.Resource<Unit>> = _updateFavorite

    init {
        val db = MovieDatabase.getInstance(application)
        movieDao = db.movieDao()

        getALLMovies()

        getFavoriteMovies()
    }

    private fun getALLMovies() {
        viewModelScope.launch {
            _movies.value = com.praktikum.tmdbmovies.utils.Resource.Loading()
            val apikey = "54e056562f9604d3a1437ea513c7d945"
            try {
                val movieResponse = MovieApiService.getApiService()
                    .getMovieList(apikey).results
                val movieEntities =
                    movieResponse.map { it.toMovieEntity() }
                movieDao?.insertMovies(movieEntities)

                val result =
                    movieDao?.getMovies()
                val data =
                    result?.map { it.toMovieViewParam() }
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
                    result?.map { it.toMovieViewParam() }
                data?.let {
                    _favoriteMovies.value =
                        com.praktikum.tmdbmovies.utils.Resource.Success(data)
                }
            } catch (e: Exception) {
                _favoriteMovies.value =
                    com.praktikum.tmdbmovies.utils.Resource.Error(e.message.toString())
            }
        }
    }

    fun updateFavorite(isFavorite: Boolean, movieId: Int) {
        viewModelScope.launch {
            try {
                val result = movieDao?.updateFavorite(
                    isFavorite,
                    movieId
                )
                result?.let {
                    _updateFavorite.value =
                        com.praktikum.tmdbmovies.utils.Resource.Success(result)
                }
            } catch (e: Exception) {
                _updateFavorite.value =
                    com.praktikum.tmdbmovies.utils.Resource.Error(e.message.toString())
            }
        }
    }
}