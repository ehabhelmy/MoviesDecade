package com.twi.moviesdecade.data.remote.service

import com.twi.moviesdecade.BuildConfig
import com.twi.moviesdecade.data.model.MovieImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerImageService {

    @GET("rest/")
    suspend fun getMovieImages(
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String = BuildConfig.FLICKER_API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("text") movieTitle: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 1
    ): MovieImagesResponse
}