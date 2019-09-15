package com.twi.moviesdecade.usecase.movies

import com.twi.moviesdecade.data.model.MovieImage
import com.twi.moviesdecade.data.model.Result
import com.twi.moviesdecade.data.movies.MoviesRepository
import javax.inject.Inject

class MovieImagesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun getMovieImages(movieTitle: String, numberOfImages: Int): Result<List<MovieImage>> =
        moviesRepository.getAllMovieImages(movieTitle, numberOfImages)

}