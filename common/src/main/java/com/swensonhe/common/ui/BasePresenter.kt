package com.swensonhe.common.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.swensonhe.common.entities.WeatherException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BasePresenter<V : BaseViewContract<*>> : BasePresenterContract<V> {

    private val _error = MutableStateFlow<WeatherException?>(null)
    override val error = _error.asStateFlow()

    private var _view: V? = null
    protected val view: V
        get() = _view ?: throw IllegalStateException("")

    override fun updateError(exception: WeatherException?) {
        _error.update { exception }
    }

    override fun attachView(view: V) {
        _view = view
    }

    override fun detachView() {
        _view = null
    }

    protected inline fun <reified T> LifecycleOwner.collectWithLifecycle(
        flow: Flow<T>,
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline collector: suspend (T) -> Unit,
    ): Job = lifecycleScope.launch {
        repeatOnLifecycle(lifecycleState) {
            flow.collectLatest { collector(it) }
        }
    }
}