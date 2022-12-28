package com.praktikum.tmdbmovies.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.praktikum.tmdbmovies.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("UPDATE movies SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(isFavorite: Boolean, id: Int)

    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE is_favorite = 1")
    suspend fun getFavoriteMovies(): List<MovieEntity>
}