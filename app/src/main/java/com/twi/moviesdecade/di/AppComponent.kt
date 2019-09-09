package com.twi.moviesdecade.di

import com.twi.moviesdecade.presentation.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(splashActivity: SplashActivity)
}


