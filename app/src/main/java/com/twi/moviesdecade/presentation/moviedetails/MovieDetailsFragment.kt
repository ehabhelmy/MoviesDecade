package com.twi.moviesdecade.presentation.moviedetails

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.R
import com.twi.moviesdecade.presentation.base.BaseFragment

class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {
    override val viewType: Int
        get() = ViewType.VIEWTYPE_SCROLL
    override val viewLayoutId: Int
        get() = R.layout.layout_movies_details

    override fun initView() {
        Toast.makeText(activity,"MovieDetails",Toast.LENGTH_LONG).show()
    }

    override fun initializeViewModel() {
        getAppComponent()?.inject(this)
        baseViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }
}