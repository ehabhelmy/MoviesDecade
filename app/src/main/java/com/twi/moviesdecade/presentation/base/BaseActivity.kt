package com.twi.moviesdecade.presentation.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.twi.moviesdecade.MoviesDecadeApp
import com.twi.moviesdecade.R
import com.twi.moviesdecade.di.AppComponent
import com.twi.moviesdecade.utils.CustomAlertDialog
import javax.inject.Inject


abstract class BaseActivity<T> : AppCompatActivity(), BaseView where T : BaseViewModel {

    protected var baseViewModel: T? = null
    protected abstract val layoutId: Int
    abstract val containerId: Int
    private var progressBar: ProgressBar? = null
    open var dialogDismissAction: (() -> Unit)? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun showError(error: String?) {
        CustomAlertDialog.showDialogError(this, getString(R.string.error), error, getString(R.string.ok), null)
    }

    override fun showSuccess(msg: String, block: (() -> Unit)?) {
        CustomAlertDialog.showDialog(this, getString(R.string.success), msg, getString(R.string.ok), block)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        progressBar = findViewById(R.id.progress_bar)
        initializeViewModel()
        subscribeLiveData()
        baseViewModel?.start()
        initView()
    }

    fun getAppComponent() : AppComponent?  = (application as? MoviesDecadeApp)?.appComponent

    protected open fun initView() {
        // will be overridden
    }

    protected abstract fun initializeViewModel()


    protected open fun subscribeLiveData() {
        baseViewModel?.errorDialog?.observe(this, Observer<String> { this.showError(it) })
        baseViewModel?.successDialog?.observe(this, Observer { this.showSuccess(it, dialogDismissAction) })
        baseViewModel?.showFullLoading?.observe(this, Observer<Boolean> { this.showLoading(it) })
    }

    override fun showLoading(aBoolean: Boolean?) = if (aBoolean!!) {
        progressBar?.visibility = View.VISIBLE
    } else {
        progressBar?.visibility = View.GONE
    }

    fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
