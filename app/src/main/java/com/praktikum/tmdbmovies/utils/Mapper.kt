package com.praktikum.tmdbmovies.utils

import com.praktikum.tmdbmovies.entity.MovieEntity
import com.praktikum.tmdbmovies.models.Movie

fun Movie.toMovieEntity(): MovieEntity{
    return MovieEntity(
        id = id,
        title = title.orEmpty(),
        poster = poster.orEmpty(),
        release = release.orEmpty(),
        language = language.orEmpty(),
        overview = overview.orEmpty(),
    )
}

fun MovieEntity.toMovie(): MovieEntity{
    return MovieEntity(
        id = id,
        title = title,
        poster = poster,
        release = release,
        language = language,
        overview = overview,
        isFavorite = isFavorite,
    )
}