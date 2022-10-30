package com.praktikum.tmdbmovies.services

import com.praktikum.tmdbmovies.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface{

    @GET("/3/movie/popular?api_key=54e056562f9604d3a1437ea513c7d945")
    fun getMovieList(): Call<MovieResponse>
}