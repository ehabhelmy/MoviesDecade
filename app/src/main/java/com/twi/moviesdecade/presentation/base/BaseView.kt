package com.twi.moviesdecade.presentation.base

/**
 * Created by Ehab on 6/13/2018.
 */

interface BaseView {

    fun showError(error: String?)

    fun showSuccess(msg: String, block: (() -> Unit)?)

    fun showLoading(aBoolean: Boolean?)
}
