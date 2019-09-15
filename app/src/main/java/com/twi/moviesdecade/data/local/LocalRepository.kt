package com.twi.moviesdecade.data.local

import android.content.Context
import com.twi.moviesdecade.data.model.Movie


interface LocalRepository {
    suspend fun loadMovies(context: Context): List<Movie>?
    suspend fun saveMovies(movies : List<Movie>?)
}
