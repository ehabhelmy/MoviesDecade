package com.twi.moviesdecade.data.movies

import android.content.Context
import com.twi.moviesdecade.data.local.LocalRepository
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.data.model.MovieImage
import com.twi.moviesdecade.data.model.Result
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteDataSource: MoviesRemoteDataSource,
    private val context: Context
) {

    suspend fun getAllMoviesUnOrdered(): List<Movie>? = localRepository.loadMovies(context)

    suspend fun getAllMovieImages(movieTitle: String, numberOfImages: Int): Result<List<MovieImage>> =
        remoteDataSource.getMovieImages(movieTitle , numberOfImages)
}