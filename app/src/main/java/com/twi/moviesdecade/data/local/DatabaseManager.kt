package com.twi.moviesdecade.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.twi.moviesdecade.data.model.Movie


@Database(entities = [Movie::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseManager? = null

        fun getDatabase(context: Context): DatabaseManager? {
            if (INSTANCE == null) {
                synchronized(DatabaseManager::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseManager::class.java, "movie_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }

    }

}
