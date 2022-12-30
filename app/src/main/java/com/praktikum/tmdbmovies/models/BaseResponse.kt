package com.praktikum.tmdbmovies.models

import com.google.gson.annotations.SerializedName

data class BaseResponse (
    @SerializedName("results")
    val results: List<Movie>
)