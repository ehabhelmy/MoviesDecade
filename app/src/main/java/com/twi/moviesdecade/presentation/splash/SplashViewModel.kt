package com.twi.moviesdecade.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twi.moviesdecade.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    val splashFinishedLiveData = MutableLiveData<Boolean>()

    override fun start() {
        viewModelScope.launch {
            delay(3000)
            splashFinishedLiveData.value = true
        }
    }


}