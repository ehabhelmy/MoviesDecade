package com.twi.moviesdecade.data.local


import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.data.model.MoviesWrapper
import java.io.IOException
import javax.inject.Inject

class LocalRepositoryImpl @Inject
constructor(
    private var preferencesManager: PreferencesManager,
    private var databaseManager: DatabaseManager
) : LocalRepository {

    var movies: List<Movie>? = null

    inline fun <reified T> Gson.fromJson(json: String) =
        this.fromJson<T>(json, object : TypeToken<T>() {}.type)


    override suspend fun loadMovies(context: Context): List<Movie>? {
        return if (movies?.isNotEmpty() == true) {
            movies
        } else {
            loadMoviesFromDataBase()
            if (movies?.isNotEmpty() == true) {
                movies
            } else {
                loadMoviesFromAssets(context)
            }
        }
    }

    private suspend fun loadMoviesFromDataBase() {
        val moviesDao = databaseManager.moviesDao()
        movies = moviesDao.getAllMovies()
    }

    private fun loadMoviesFromAssets(context: Context): List<Movie>? {
        return try {
            val inputStream = context.assets.open("movies.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            movies = Gson().fromJson<MoviesWrapper>(
                String(buffer, Charsets.UTF_8)
            ).movies
//            saveMovies(movies)
            movies
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    override suspend fun saveMovies(movies: List<Movie>?) {
        movies?.let {
            val moviesDao = databaseManager.moviesDao()
            moviesDao.insertMovies(it)
        }
    }
}
