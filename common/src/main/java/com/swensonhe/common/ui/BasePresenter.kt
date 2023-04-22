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

    protected val collectorJobs = mutableListOf<Job>()

    protected val error = MutableStateFlow<WeatherException?>(null)

    private var _view: V? = null
    protected val view: V
        get() = _view ?: throw IllegalStateException("")

    override fun updateError(exception: WeatherException?) {
        if (exception == null) error.update { null }
        error.update { WeatherException(
            messageResId = exception?.messageResId,
            message = exception?.message
        ) }
    }

    override fun attachView(view: V) {
        _view = view
    }

    override fun detachView() {
        _view = null
        collectorJobs.clear()
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