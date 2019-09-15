package com.twi.moviesdecade.presentation.moviedetails

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.R
import com.twi.moviesdecade.presentation.base.BaseFragment
import com.twi.moviesdecade.presentation.moviedetails.MovieDetailsFragmentArgs.Companion.fromBundle
import kotlinx.android.synthetic.main.layout_movies_details.*

class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {
    override val viewType: Int
        get() = ViewType.VIEWTYPE_SCROLL
    override val viewLayoutId: Int
        get() = R.layout.layout_movies_details

    val movie by lazy {
        arguments?.let {
            fromBundle(it).movie
        }
    }

    private val imagePagerAdapter by lazy {
        activity?.run {
            ImagePagerAdapter(this)
        }
    }

    override fun initView() {
        tabDots.setupWithViewPager(image_viewer, true)
        image_viewer.adapter = imagePagerAdapter
        baseViewModel?.getMovieImages(movie)
        setupMovieDetails()
    }

    private fun setupMovieDetails() {
        movie_title.text = movie?.title
        movie_year.text = movie?.year.toString()
        movie?.rating?.let {
            movie_rating.rating = it.toFloat()
        }
        movie_genres.text = StringBuilder().apply {
            movie?.genres?.forEach {
                append(it)
                append(", ")
            }
            deleteCharAt(lastIndex)
            deleteCharAt(lastIndex)
        }
        movie_cast.text = StringBuilder().apply {
            movie?.cast?.forEach {
                append(it)
                append(", ")
            }
            deleteCharAt(lastIndex)
            deleteCharAt(lastIndex)
        }
    }

    override fun initializeViewModel() {
        getAppComponent()?.inject(this)
        baseViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    override fun subscribeLiveData() {
        super.subscribeLiveData()
        baseViewModel?.movieImagesLiveData?.observe(this, Observer {
            imagePagerAdapter?.update(it)
        })
    }
}