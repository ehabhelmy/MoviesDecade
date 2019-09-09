package com.twi.moviesdecade

import android.app.Application
import com.twi.moviesdecade.di.AppComponent
import com.twi.moviesdecade.di.AppModule
import com.twi.moviesdecade.di.DaggerAppComponent
import com.twi.moviesdecade.utils.LogUtils



class MoviesDecadeApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        LogUtils.init()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(application = applicationContext)).build()
    }

    companion object {
        lateinit var instance: MoviesDecadeApp
            private set
    }

}