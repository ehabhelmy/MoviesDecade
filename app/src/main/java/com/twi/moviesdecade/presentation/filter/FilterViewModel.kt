package com.twi.moviesdecade.presentation.filter

import androidx.lifecycle.MutableLiveData
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.presentation.base.BaseViewModel
import com.twi.moviesdecade.usecase.movies.MoviesUseCase
import javax.inject.Inject

class FilterViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    BaseViewModel() {

    val filterMoviesLiveData = MutableLiveData<List<Movie>>()

    fun filter(currentlySelectedYear: Int) {
        showFullLoading.value = true
        executeOnBackground {
            val movies = moviesUseCase.getAllmoviesUnOrdered()
            val filteredMovies = movies?.filter { movie ->
                movie.year == currentlySelectedYear
            }?.sortedBy {
                it.rating
            }?.takeLast(5)
            executeOnForeground {
                showFullLoading.value = false
                filterMoviesLiveData.value = filteredMovies
            }
        }
    }
}