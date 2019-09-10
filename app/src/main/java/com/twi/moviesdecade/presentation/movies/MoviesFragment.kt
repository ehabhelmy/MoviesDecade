package com.twi.moviesdecade.presentation.movies

import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.R
import com.twi.moviesdecade.presentation.base.BaseFragment

class MoviesFragment : BaseFragment<MoviesViewModel>(){
    override val viewType: Int
        get() = ViewType.VIEWTYPE_FRAME
    override val viewLayoutId: Int
        get() = R.layout.layout_movies

    override fun initView() {
    }

    override fun initializeViewModel() {
        getAppComponent()?.inject(this)
        baseViewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
    }
}