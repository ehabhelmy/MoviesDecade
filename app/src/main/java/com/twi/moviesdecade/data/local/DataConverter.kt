package com.twi.moviesdecade.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twi.moviesdecade.data.model.MovieImage


class DataConverter {

    @TypeConverter
    fun fromList(cast: List<String>?): String? {
        if (cast == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(cast, type)
    }

    @TypeConverter
    fun toList(cast: String?): List<String>? {
        if (cast == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(cast, type)
    }

    @TypeConverter
    fun fromMovieImages(images: List<MovieImage>?): String? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<MovieImage>>() {}.type
        return gson.toJson(images, type)
    }

    @TypeConverter
    fun toMovieImages(images: String?): List<MovieImage>? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<MovieImage>>() {}.type
        return gson.fromJson(images, type)
    }

}