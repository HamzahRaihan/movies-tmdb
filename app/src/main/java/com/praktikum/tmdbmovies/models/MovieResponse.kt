package com.praktikum.tmdbmovies.models

import com.google.gson.annotations.SerializedName
import com.praktikum.tmdbmovies.entity.MovieEntity

data class MovieResponse(
    @SerializedName("results")
    val movies : List<MovieEntity>
)