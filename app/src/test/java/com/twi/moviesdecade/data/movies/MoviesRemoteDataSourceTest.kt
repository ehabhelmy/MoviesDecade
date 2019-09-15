package com.twi.moviesdecade.data.movies

import com.twi.moviesdecade.data.model.MovieImage
import com.twi.moviesdecade.data.model.MovieImagesResponse
import com.twi.moviesdecade.data.model.MovieImagesWrapper
import com.twi.moviesdecade.data.model.Result
import com.twi.moviesdecade.data.remote.ServiceGenerator
import com.twi.moviesdecade.data.remote.service.FlickerImageService
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MoviesRemoteDataSourceTest {

    val images by lazy {
        MovieImagesResponse(MovieImagesWrapper(listOf()))
    }
    val sourceSuccess = object : FlickerImageService {
        override suspend fun getMovieImages(
            method: String,
            apiKey: String,
            format: String,
            noJsonCallback: Int,
            movieTitle: String,
            page: Int,
            perPage: Int
        ): MovieImagesResponse {
            return images
        }

    }

    @Test
    fun getMovieImagesSuccess() = runBlocking {
        val mockedServiceGenerator = mockk<ServiceGenerator>()
        val moviesRemoteDataSource = MoviesRemoteDataSource(mockedServiceGenerator)
        every {
            mockedServiceGenerator.createService(
                FlickerImageService::class.java,
                any()
            )
        } answers {
            sourceSuccess
        }
        val result = moviesRemoteDataSource.getMovieImages("troy", 1)
        assert(result is Result.Success)
    }

    @Test
    fun getMovieImagesError() = runBlocking {
        val mockedServiceGenerator = mockk<ServiceGenerator>()
        val moviesRemoteDataSource = MoviesRemoteDataSource(mockedServiceGenerator)
        every {
            mockedServiceGenerator.createService(
                FlickerImageService::class.java,
                any()
            )
        } answers {
            throw Exception()
        }
        var result : Result<List<MovieImage>>? = null
        try {
            result = moviesRemoteDataSource.getMovieImages("troy", 1)
        }catch (e : Exception) {
            assert(result is Result.Error)
        }

    }


}