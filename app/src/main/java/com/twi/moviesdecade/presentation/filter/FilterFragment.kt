package com.twi.moviesdecade.presentation.filter

import android.app.DatePickerDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twi.moviesdecade.R
import com.twi.moviesdecade.data.model.Movie
import com.twi.moviesdecade.presentation.base.BaseFragment
import com.twi.moviesdecade.widget.YearPickerDialog
import kotlinx.android.synthetic.main.layout_filter.*

class FilterFragment : BaseFragment<FilterViewModel>(), FilterAdapter.OnItemClick {
    override fun onItemClicked(node: Movie?) {
        findNavController().navigate(
            FilterFragmentDirections.actionFilterFragmentToMovieDetailsFragment(
                node
            )
        )
    }

    override val viewType: Int
        get() = ViewType.VIEWTYPE_FRAME
    override val viewLayoutId: Int
        get() = R.layout.layout_filter

    var currentlySelectedYear = 2018

    private val filterAdapter by lazy {
        FilterAdapter()
    }

    override fun initView() {
        setupRecyclerView()
        filterMoviesPerCurrentSelectedYear()
        year_selector.setOnClickListener {
            fragmentManager?.run {
                val dialog = YearPickerDialog()
                dialog.setListener(DatePickerDialog.OnDateSetListener { _, year, _, _ ->
                    currentlySelectedYear = year
                    year_selected.text = currentlySelectedYear.toString()
                    filterMoviesPerCurrentSelectedYear()
                })
                dialog.show(this, "dialog")
            }
        }
    }

    private fun setupRecyclerView() {
        list.layoutManager = LinearLayoutManager(activity)
        filterAdapter.onItemClick = this
        list.adapter = filterAdapter
    }

    private fun filterMoviesPerCurrentSelectedYear() {
        baseViewModel?.filter(currentlySelectedYear)
    }

    override fun initializeViewModel() {
        getAppComponent()?.inject(this)
        baseViewModel = ViewModelProvider(this, viewModelFactory).get(FilterViewModel::class.java)
    }

    override fun subscribeLiveData() {
        super.subscribeLiveData()
        baseViewModel?.filterMoviesLiveData?.observe(this, Observer {
            filterAdapter.update(it)
        })
    }
}