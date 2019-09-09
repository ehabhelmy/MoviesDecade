package com.twi.moviesdecade.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.presentation.base.ViewModelFactory
import com.twi.moviesdecade.presentation.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
    //You are able to declare ViewModelProvider.Factory dependency in another module. For example in ApplicationModule.

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    //Others ViewModels
}