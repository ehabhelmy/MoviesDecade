package com.twi.moviesdecade.presentation.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.annotation.IntDef
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.MoviesDecadeApp
import com.twi.moviesdecade.R
import com.twi.moviesdecade.di.AppComponent
import com.twi.moviesdecade.presentation.base.BaseFragment.ViewType.Companion.VIEWTYPE_FRAME
import com.twi.moviesdecade.presentation.base.BaseFragment.ViewType.Companion.VIEWTYPE_PULL
import com.twi.moviesdecade.presentation.base.BaseFragment.ViewType.Companion.VIEWTYPE_SCROLL
import com.twi.moviesdecade.presentation.base.BaseFragment.ViewType.Companion.VIEWTYPE_SCROLL_TOOLBAR
import com.twi.moviesdecade.utils.CustomAlertDialog
import javax.inject.Inject


abstract class BaseFragment<T : BaseViewModel> : Fragment(), BaseView {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var fragmentView: View? = null
    protected var baseViewModel: T? = null
    protected open lateinit var rootView: View
    protected open lateinit var progressBar: ProgressBar
    protected open lateinit var viewStub: ViewStub
    protected open var containerType: Int = 0
    protected open val mainContainerLayoutId: Int
        get() {
            return when (containerType) {
                VIEWTYPE_SCROLL_TOOLBAR -> R.layout.layout_fragment_scroll_toolbar
                VIEWTYPE_SCROLL -> R.layout.layout_fragment_scroll_view
                VIEWTYPE_FRAME -> R.layout.layout_fragment_frame
                VIEWTYPE_PULL -> R.layout.layout_fragment_pull_to_refresh
                else -> R.layout.layout_fragment_frame
            }
        }

    @get:ViewType
    protected abstract val viewType: Int


    protected abstract val viewLayoutId: Int
    open var dialogDismissAction: (() -> Unit)? = null

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(VIEWTYPE_SCROLL_TOOLBAR, VIEWTYPE_SCROLL, VIEWTYPE_FRAME, VIEWTYPE_PULL)
    internal annotation class ViewType {
        companion object {
            const val VIEWTYPE_SCROLL_TOOLBAR = 1
            const val VIEWTYPE_SCROLL = 2
            const val VIEWTYPE_FRAME = 3
            const val VIEWTYPE_PULL = 4
        }
    }

    override fun showError(error: String?) {
        CustomAlertDialog.showDialogError(
            activity!!, getString(com.twi.moviesdecade.R.string.error), error, getString(
                com.twi.moviesdecade.R.string.ok
            ), null
        )
    }

    override fun showSuccess(msg: String, block: (() -> Unit)?) {
        CustomAlertDialog.showDialog(
            activity!!, getString(com.twi.moviesdecade.R.string.success), msg, getString(
                com.twi.moviesdecade.R.string.ok
            ), block
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.containerType = viewType
        rootView = inflater.inflate(mainContainerLayoutId, container, false)
        viewStub = rootView.findViewById(R.id.content_vs)
        progressBar = rootView.findViewById(R.id.progress_bar)
        viewStub.layoutResource = viewLayoutId
        fragmentView = viewStub.inflate()
        initializeViewModel()
        subscribeLiveData()
        baseViewModel?.start()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun getAppComponent() : AppComponent?  = (activity?.application as? MoviesDecadeApp)?.appComponent

    protected abstract fun initView()

    protected abstract fun initializeViewModel()

    protected open fun subscribeLiveData() {
        baseViewModel?.errorDialog?.observe(this, Observer<String> { this.showError(it) })
        baseViewModel?.successDialog?.observe(
            this,
            Observer { this.showSuccess(it, dialogDismissAction) })
        baseViewModel?.showFullLoading?.observe(this, Observer<Boolean> { this.showLoading(it) })
    }

    override fun showLoading(aBoolean: Boolean?) = if (aBoolean != null && aBoolean) {
        progressBar.visibility = View.VISIBLE
    } else {
        progressBar.visibility = View.GONE
    }

    fun hideKeyboard() {
        val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity?.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


