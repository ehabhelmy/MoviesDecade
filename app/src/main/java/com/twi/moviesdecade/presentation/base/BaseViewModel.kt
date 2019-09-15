package com.twi.moviesdecade.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main
    var errorDialog: MutableLiveData<String> = MutableLiveData()
    var successDialog: MutableLiveData<String> = MutableLiveData()
    var showFullLoading: MutableLiveData<Boolean> = MutableLiveData()

    open fun start() {
        // called when we want to fetch data in initialization
    }
    protected fun executeOnBackground(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(context = backgroundContext, block = block)
    }

    protected suspend fun executeOnForeground(block: suspend CoroutineScope.() -> Unit) {
        withContext(context = foregroundContext, block = block)
    }

}
