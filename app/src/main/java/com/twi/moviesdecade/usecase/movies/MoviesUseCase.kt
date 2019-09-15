package com.twi.moviesdecade.usecase.movies

import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.data.movies.MoviesRepository
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun getAllmoviesUnOrdered(): List<Movie>? = moviesRepository.getAllMoviesUnOrdered()


}