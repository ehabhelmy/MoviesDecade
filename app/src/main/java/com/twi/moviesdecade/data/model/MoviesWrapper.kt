package com.twi.moviesdecade.data.model

import com.google.gson.annotations.SerializedName

data class MoviesWrapper(@SerializedName("movies") val movies: List<Movie>)