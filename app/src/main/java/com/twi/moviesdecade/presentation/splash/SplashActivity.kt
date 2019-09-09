package com.twi.moviesdecade.presentation.splash

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.R
import com.twi.moviesdecade.presentation.base.BaseActivity

class SplashActivity(override val layoutId: Int = R.layout.layout_splash, override val containerId: Int = 0) :
    BaseActivity<SplashViewModel>() {

    override fun initializeViewModel() {
        getAppComponent()?.inject(this)
        baseViewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }


    override fun subscribeLiveData() {
        super.subscribeLiveData()
        baseViewModel?.splashFinishedLiveData?.observe(this, Observer {
            Toast.makeText(this,"asdasdasdasdas",Toast.LENGTH_LONG).show()
        })
    }
}