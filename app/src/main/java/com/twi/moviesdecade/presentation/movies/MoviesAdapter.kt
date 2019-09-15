package com.twi.moviesdecade.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.twi.moviesdecade.R
import com.twi.moviesdecade.data.model.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_movie_card.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    interface OnItemClick {
        fun onItemClicked(node: Movie?)
    }

    var onItemClick: OnItemClick? = null
    var data: MutableList<Movie> = mutableListOf()

    fun updateMovies(movies: MutableList<Movie>) {
        data.clear()
        data.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_movie_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(item: Movie?) {
            containerView.movie_title.text = item?.title
            containerView.movie_date.text = item?.year.toString()
            item?.rating?.toFloat()?.let {
                containerView.movie_rating.rating = it
            }
            if (item?.movieImages?.isNotEmpty() == true) {
                val it = item.movieImages?.get(0)
                val url =
                    "http://farm${it?.farm}.static.flickr.com/${it?.server}/${it?.id}_${it?.secret}.jpg"
                Glide.with(containerView.context).load(url)
                    .apply(RequestOptions().placeholder(R.mipmap.image))
                    .into(containerView.movie_image)
            }
        }
    }
}
