package com.twi.moviesdecade.presentation.movies

import androidx.lifecycle.MutableLiveData
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.data.model.Result
import com.twi.moviesdecade.presentation.base.BaseViewModel
import com.twi.moviesdecade.usecase.movies.MovieImagesUseCase
import com.twi.moviesdecade.usecase.movies.MoviesUseCase
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val movieImagesUseCase: MovieImagesUseCase
) :
    BaseViewModel() {

    var moviesLiveData = MutableLiveData<List<Movie>>()

    override fun start() {
        showFullLoading.value = true
        executeOnBackground {
            val movies = moviesUseCase.getAllmoviesUnOrdered()
            val latestMovies = movies?.takeLast(20)
            latestMovies?.forEach { movie ->
                movie.title?.run {
                    val result =
                        movieImagesUseCase.getMovieImages(movieTitle = this, numberOfImages = 1)
                    if (result is Result.Success) {
                        movie.movieImages = result.data
                    }
                }
            }
            executeOnForeground {
                showFullLoading.value = false
                if (movies != null) {
                    moviesLiveData.value = latestMovies
                } else {
                    errorDialog.value = "No movies"
                }
            }
        }
    }
}