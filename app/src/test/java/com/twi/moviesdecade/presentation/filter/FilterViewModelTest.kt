package com.twi.moviesdecade.presentation.filter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.usecase.movies.MoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class FilterViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val topMovie = Movie(null, 2019, null, null, 5)

    private val movies by lazy {
        listOf(
            topMovie,
            Movie(null, 2018, null, null, 4),
            Movie(null, 2019, null, null, 2),
            Movie(null, 2019, null, null, 5),
            Movie(null, 2016, null, null, 5),
            Movie(null, 2019, null, null, 3),
            Movie(null, 2019, null, null, 5),
            Movie(null, 2019, null, null, 5),
            Movie(null, 2012, null, null, 2),
            Movie(null, 2019, null, null, 1),
            Movie(null, 2019, null, null, 5),
            Movie(null, 2019, null, null, 4)
        )
    }


    private val mockedMoviesUseCase by lazy {
        mockk<MoviesUseCase>()
    }

    @Test
    fun filter() = runBlocking {
        coEvery { mockedMoviesUseCase.getAllmoviesUnOrdered() } coAnswers { movies }
        val filterViewModel = FilterViewModel(mockedMoviesUseCase)
        filterViewModel.backgroundContext = Dispatchers.Unconfined
        filterViewModel.foregroundContext = Dispatchers.Unconfined
        filterViewModel.filter(2019)
        assert(filterViewModel.filterMoviesLiveData.value?.get(0) == topMovie)
    }
}