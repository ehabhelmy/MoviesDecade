package com.twi.moviesdecade.presentation.moviedetails

import androidx.lifecycle.MutableLiveData
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.data.model.MovieImage
import com.twi.moviesdecade.data.model.Result
import com.twi.moviesdecade.presentation.base.BaseViewModel
import com.twi.moviesdecade.usecase.movies.MovieImagesUseCase
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val movieImagesUseCase: MovieImagesUseCase) :
    BaseViewModel() {

    val movieImagesLiveData = MutableLiveData<List<MovieImage>>()

    fun getMovieImages(movie: Movie?) {
        showFullLoading.value = true
        executeOnBackground {
            movie?.title?.let {
                val result = movieImagesUseCase.getMovieImages(movieTitle = it, numberOfImages = 10)
                executeOnForeground {
                    showFullLoading.value = false
                    if (result is Result.Success) {
                        movieImagesLiveData.value = result.data
                    } else {
                        errorDialog.value = "Error in fetching images"
                    }
                }
            }
        }
    }
}