package com.praktikum.tmdbmovies.entity

// Hamzah Raihan Ikhsanul Fikri
// 2010631250052

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "original_language")
    val language: String,

    @ColumnInfo(name = "poster_path")
    val poster: String,

    @ColumnInfo(name = "release_date")
    val release: String,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
)