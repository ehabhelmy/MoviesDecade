package com.twi.moviesdecade.data.movies

import android.content.Context
import com.twi.moviesdecade.data.local.LocalRepository
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.data.model.MovieImage
import com.twi.moviesdecade.data.model.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MoviesRepositoryTest {

    private val mockedRemoteDataSource by lazy {
        mockk<MoviesRemoteDataSource>()
    }

    private val mockedLocalDataSource by lazy {
        mockk<LocalRepository>()
    }

    private val mockedContext by lazy {
        mockk<Context>()
    }

    @Test
    fun getAllMoviesUnOrdered() = runBlocking {
        val movies = listOf<Movie>()
        coEvery { mockedLocalDataSource.loadMovies(mockedContext) } coAnswers { movies }
        val moviesRepository =
            MoviesRepository(mockedLocalDataSource, mockedRemoteDataSource, mockedContext)
        val gottenMovies = moviesRepository.getAllMoviesUnOrdered()
        assert(gottenMovies == movies)
    }

    @Test
    fun getAllMovieImages() = runBlocking {
        val movieImages = listOf<MovieImage>()
        coEvery { mockedRemoteDataSource.getMovieImages(any(), any()) } coAnswers {
            Result.Success(
                movieImages
            )
        }
        val moviesRepository =
            MoviesRepository(mockedLocalDataSource, mockedRemoteDataSource, mockedContext)
        val result = moviesRepository.getAllMovieImages("troy", 1)
        assert(result is Result.Success)
    }
}