package com.twi.moviesdecade.di

import android.content.Context
import android.content.SharedPreferences
import com.twi.moviesdecade.data.local.DatabaseManager
import com.twi.moviesdecade.data.local.PreferencesManager
import com.twi.moviesdecade.utils.constants.PrefrenceConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule(private val application : Context) {

    val context: Context
        @Singleton
        @Provides
        get() = application


    @Provides
    fun getPreferencesManager(sharedPreferences: SharedPreferences): PreferencesManager {
        return PreferencesManager(sharedPreferences)
    }

    @Provides
    fun provideDatabase(): DatabaseManager? {
        return DatabaseManager.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PrefrenceConstants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
    }


}
