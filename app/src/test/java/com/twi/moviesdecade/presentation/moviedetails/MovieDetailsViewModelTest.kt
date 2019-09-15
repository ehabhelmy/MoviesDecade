package com.twi.moviesdecade.presentation.moviedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.data.model.MovieImage
import com.twi.moviesdecade.data.model.Result
import com.twi.moviesdecade.usecase.movies.MovieImagesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test

class MovieDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val moviesImages by lazy {
        listOf<MovieImage>()
    }

    private val mockedMoviesImagesUseCase by lazy {
        mockk<MovieImagesUseCase>()
    }

    @Test
    fun getMovieImages() {
        coEvery {
            mockedMoviesImagesUseCase.getMovieImages(
                any(),
                any()
            )
        } coAnswers { Result.Success(moviesImages) }
        val movieDetailsViewModel = MovieDetailsViewModel(mockedMoviesImagesUseCase)
        movieDetailsViewModel.backgroundContext = Dispatchers.Unconfined
        movieDetailsViewModel.foregroundContext = Dispatchers.Unconfined
        movieDetailsViewModel.getMovieImages(Movie("troy", null, null, null, null))
        assert(movieDetailsViewModel.movieImagesLiveData.value == moviesImages)
    }

}