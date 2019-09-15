package com.twi.moviesdecade.presentation.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.usecase.movies.MovieImagesUseCase
import com.twi.moviesdecade.usecase.movies.MoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val movies by lazy {
        listOf<Movie>()
    }

    private val mockedMoviesUseCase by lazy {
        mockk<MoviesUseCase>()
    }

    private val mockedMoviesImagesUseCase by lazy {
        mockk<MovieImagesUseCase>()
    }

    @Test
    fun start() {
        coEvery { mockedMoviesUseCase.getAllmoviesUnOrdered() } coAnswers { movies }
        val moviesViewModel = MoviesViewModel(mockedMoviesUseCase, mockedMoviesImagesUseCase)
        moviesViewModel.backgroundContext = Dispatchers.Unconfined
        moviesViewModel.foregroundContext = Dispatchers.Unconfined
        moviesViewModel.start()
        assert(moviesViewModel.moviesLiveData.value == movies)
    }
}