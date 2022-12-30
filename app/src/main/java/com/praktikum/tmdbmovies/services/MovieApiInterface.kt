package com.praktikum.tmdbmovies.services

import com.praktikum.tmdbmovies.models.BaseResponse
import com.praktikum.tmdbmovies.models.MovieDetailData
import com.praktikum.tmdbmovies.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface{

    @GET("/3/movie/popular")
    fun getMovieList(
        @Query("api_key") apikey: String
    ): BaseResponse

    @GET("/3/movie/popular")
    fun getMovieListRando(
        @Query("api_key") apikey: String
    ): Call<BaseResponse>

    fun getMovieListResult(
        @Query("api_key") apikey: String
    ): Call<MovieResponse>

    @GET("/3/movie/tt10403420")
    fun getMovieId(
        @Query("imdb_id") id: String?,
        @Query("api_key") apikey: String
    ): Call<MovieDetailData>
}