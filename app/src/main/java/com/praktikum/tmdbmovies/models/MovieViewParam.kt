package com.praktikum.tmdbmovies.models

data class MovieViewParam(
    val id: Int,
    val title: String,
    val overview: String,
    val language: String,
    val poster: String,
    val release: String,
    val isFavorite: Boolean,
)