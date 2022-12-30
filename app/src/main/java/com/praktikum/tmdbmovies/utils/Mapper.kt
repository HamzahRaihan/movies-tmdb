package com.praktikum.tmdbmovies.utils

import com.praktikum.tmdbmovies.entity.MovieEntity
import com.praktikum.tmdbmovies.models.Movie
import com.praktikum.tmdbmovies.models.MovieResponse
import com.praktikum.tmdbmovies.models.MovieViewParam

//fun MovieViewParam.toMovieEntity() = MovieEntity(
//    id = id,
//    title = title,
//    overview = overview,
//    language = language,
//    poster = poster,
//    release = release,
//)

fun Movie.toMovieEntity(): MovieEntity{
    return MovieEntity(
        id = id,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        language = language.orEmpty(),
        poster = poster.orEmpty(),
        release = release.orEmpty(),
    )
}


fun MovieEntity.toMovieViewParam(): MovieViewParam{
    return MovieViewParam(
        id = id,
        title = title,
        poster = poster,
        release = release,
        language = language,
        overview = overview,
        isFavorite = isFavorite,
    )
}