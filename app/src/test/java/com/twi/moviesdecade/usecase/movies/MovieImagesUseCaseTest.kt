package com.twi.moviesdecade.usecase.movies

import com.twi.moviesdecade.data.model.MovieImage
import com.twi.moviesdecade.data.model.Result
import com.twi.moviesdecade.data.movies.MoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieImagesUseCaseTest {

    @Test
    fun getMovieImagesSuccess() = runBlocking {
        val movieImagesList = listOf<MovieImage>()
        val mockedRepo = mockk<MoviesRepository>()
        coEvery { mockedRepo.getAllMovieImages(any(), any()) } coAnswers {
            Result.Success(
                movieImagesList
            )
        }
        val movieImagesUseCase = MovieImagesUseCase(mockedRepo)
        val result = movieImagesUseCase.getMovieImages("troy", 1)
        assert(result is Result.Success)
        if (result is Result.Success) {
            assert(result.data == movieImagesList)
        }
    }

    @Test
    fun getMovieImagesError() = runBlocking {
        val mockedRepo = mockk<MoviesRepository>()
        coEvery { mockedRepo.getAllMovieImages(any(), any()) } coAnswers {
            Result.Error(Exception())
        }
        val movieImagesUseCase = MovieImagesUseCase(mockedRepo)
        val result = movieImagesUseCase.getMovieImages("troy", 1)
        assert(result is Result.Error)
    }
}