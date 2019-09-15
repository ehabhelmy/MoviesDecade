package com.twi.moviesdecade.data.movies

import com.twi.moviesdecade.BuildConfig
import com.twi.moviesdecade.data.model.MovieImage
import com.twi.moviesdecade.data.model.Result
import com.twi.moviesdecade.data.remote.ServiceGenerator
import com.twi.moviesdecade.data.remote.service.FlickerImageService
import javax.inject.Inject


/**
 * Created by ehab on 4/21/2018.
 */

class MoviesRemoteDataSource @Inject constructor(private val serviceGenerator: ServiceGenerator) {

    suspend fun getMovieImages(movieTitle: String, numberOfImages: Int): Result<List<MovieImage>> {
        return try {
            val movieImagesService = serviceGenerator.createService(
                FlickerImageService::class.java,
                BuildConfig.IMAGE_URL
            )
            Result.Success(
                movieImagesService.getMovieImages(
                    movieTitle = movieTitle,
                    perPage = numberOfImages
                ).photos.images
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }

}