package com.twi.moviesdecade.presentation.moviedetails

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.twi.moviesdecade.R
import com.twi.moviesdecade.data.model.MovieImage

class ImagePagerAdapter(private val context: Context) : PagerAdapter() {

    var images: MutableList<MovieImage> = mutableListOf()

    override fun getCount(): Int = images.size

    override fun isViewFromObject(view: View, item: Any): Boolean {
        return view === item
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.adjustViewBounds = true
        imageView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        val url =
            "http://farm${images[position].farm}.static.flickr.com/${images[position].server}/${images[position].id}_${images[position].secret}.jpg"
        Glide.with(context).load(url)
            .apply(RequestOptions().placeholder(R.mipmap.image).error(R.mipmap.image))
            .into(imageView)
        container.addView(imageView)
        return imageView
    }

    fun update(movieImages: List<MovieImage>?) {
        movieImages?.let {
            images.clear()
            images.addAll(movieImages)
            notifyDataSetChanged()
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //
    }
}