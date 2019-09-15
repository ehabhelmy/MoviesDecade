package com.twi.moviesdecade.data.model

import androidx.room.Embedded
import androidx.room.Relation


class MovieAndAllImages {

    @Embedded
    var movie: Movie? = null

    @Relation(parentColumn = "id", entityColumn = "movieId", entity = MovieImage::class)
    var movieImages: List<MovieImage>? = null
}