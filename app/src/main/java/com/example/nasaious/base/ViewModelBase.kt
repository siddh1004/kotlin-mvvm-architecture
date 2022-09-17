package com.example.nasaious.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaious.data.common.utils.Connectivity
import com.example.nasaious.data.common.utils.ContextProvider
import com.example.nasaious.data.common.utils.CoroutineContextProvider
import com.example.nasaious.data.common.utils.TestCoroutineContextProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class ViewModelBase<T : Any> : ViewModel() {

    @Inject
    lateinit var coroutineContext: ContextProvider

    @Inject
    lateinit var connectivity: Connectivity

    @Suppress("PropertyName")
    protected val _viewState = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>>
        get() = _viewState

    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
        _viewState.value = Loading()
        if (connectivity.hasNetworkAccess()) {
            viewModelScope.launch(coroutineContext.main) { action() }
        } else {
            noInternetAction()
        }
    }

    protected fun executeUseCase(action: suspend () -> Unit) {
        _viewState.value = Loading()
        viewModelScope.launch(coroutineContext.main) { action() }
    }
}