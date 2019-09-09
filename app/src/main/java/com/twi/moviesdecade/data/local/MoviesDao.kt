package com.twi.moviesdecade.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twi.moviesdecade.data.model.Movie


@Dao
interface MoviesDao {

    @Query("SELECT * from movie_table")
    suspend fun getAllMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<Movie>)
}