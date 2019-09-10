package com.twi.moviesdecade.presentation.filter

import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.R
import com.twi.moviesdecade.presentation.base.BaseFragment

class FilterFragment : BaseFragment<FilterViewModel>(){
    override val viewType: Int
        get() = ViewType.VIEWTYPE_FRAME
    override val viewLayoutId: Int
        get() = R.layout.layout_filter

    override fun initView() {
    }

    override fun initializeViewModel() {
        getAppComponent()?.inject(this)
        baseViewModel = ViewModelProvider(this, viewModelFactory).get(FilterViewModel::class.java)
    }
}