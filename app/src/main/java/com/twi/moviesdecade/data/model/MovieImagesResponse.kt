package com.twi.moviesdecade.data.model

import com.google.gson.annotations.SerializedName

data class MovieImagesResponse(@SerializedName("photos") val photos: MovieImagesWrapper)
data class MovieImagesWrapper(@SerializedName("photo") val images: List<MovieImage>)