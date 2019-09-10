package com.twi.moviesdecade.di

import com.twi.moviesdecade.presentation.filter.FilterFragment
import com.twi.moviesdecade.presentation.home.HomeActivity
import com.twi.moviesdecade.presentation.moviedetails.MovieDetailsFragment
import com.twi.moviesdecade.presentation.movies.MoviesFragment
import com.twi.moviesdecade.presentation.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(filterFragment: FilterFragment)
    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}


