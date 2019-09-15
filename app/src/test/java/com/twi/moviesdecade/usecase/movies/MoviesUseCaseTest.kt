package com.twi.moviesdecade.usecase.movies

import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.data.movies.MoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MoviesUseCaseTest {

    @Test
    fun getAllmoviesUnOrderedSuccess() = runBlocking {
        val movies = listOf<Movie>()
        val mockedRepo = mockk<MoviesRepository>()
        coEvery { mockedRepo.getAllMoviesUnOrdered() } coAnswers {
            movies
        }
        val moviesUseCase = MoviesUseCase(mockedRepo)
        val gottenMovies = moviesUseCase.getAllmoviesUnOrdered()
        assert(gottenMovies != null)
        assert(gottenMovies == movies)
    }

    @Test
    fun getAllmoviesUnOrderedError() = runBlocking {
        val mockedRepo = mockk<MoviesRepository>()
        coEvery { mockedRepo.getAllMoviesUnOrdered() } coAnswers {
            null
        }
        val moviesUseCase = MoviesUseCase(mockedRepo)
        val gottenMovies = moviesUseCase.getAllmoviesUnOrdered()
        assert(gottenMovies == null)
    }
}