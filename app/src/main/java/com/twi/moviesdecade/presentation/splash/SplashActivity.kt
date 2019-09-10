package com.twi.moviesdecade.presentation.splash

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.R
import com.twi.moviesdecade.presentation.base.BaseActivity
import com.twi.moviesdecade.presentation.home.HomeActivity

class SplashActivity(override val layoutId: Int = R.layout.layout_splash, override val containerId: Int = 0) :
    BaseActivity<SplashViewModel>() {

    override fun initializeViewModel() {
        getAppComponent()?.inject(this)
        baseViewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }


    override fun subscribeLiveData() {
        super.subscribeLiveData()
        baseViewModel?.splashFinishedLiveData?.observe(this, Observer {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        })
    }
}