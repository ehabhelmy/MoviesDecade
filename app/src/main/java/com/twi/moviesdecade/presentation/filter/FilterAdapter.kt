package com.twi.moviesdecade.presentation.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twi.moviesdecade.R
import com.twi.moviesdecade.data.model.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_movie_card.view.*

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    interface OnItemClick {
        fun onItemClicked(node: Movie?)
    }

    var onItemClick: OnItemClick? = null
    var data: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_movie_row,
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

    fun update(movies: List<Movie>?) {
        movies?.let {
            data.clear()
            data.addAll(it.toMutableList())
            notifyDataSetChanged()
        }
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
            containerView.setOnClickListener {
                onItemClick?.onItemClicked(item)
            }
        }
    }
}
