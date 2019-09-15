package com.twi.moviesdecade.presentation.movies

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.twi.moviesdecade.R
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.layout_movies.*

class MoviesFragment : BaseFragment<MoviesViewModel>(), MoviesAdapter.OnItemClick {

    override val viewType: Int
        get() = ViewType.VIEWTYPE_FRAME
    override val viewLayoutId: Int
        get() = R.layout.layout_movies


    lateinit var moviesAdapter: MoviesAdapter


    override fun initView() {
        list.layoutManager = GridLayoutManager(activity,2)
        moviesAdapter = MoviesAdapter()
        moviesAdapter.onItemClick = this
        list.adapter = moviesAdapter
    }

    override fun initializeViewModel() {
        getAppComponent()?.inject(this)
        baseViewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onItemClicked(node: Movie?) {
        //
    }

    override fun subscribeLiveData() {
        super.subscribeLiveData()
        baseViewModel?.moviesLiveData?.observe(this, Observer { movies ->
            moviesAdapter.updateMovies(movies.toMutableList())
        })
    }
}