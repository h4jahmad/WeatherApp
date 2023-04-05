package com.swensonhe.common.ui

import com.swensonhe.common.entities.WeatherException
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BasePresenter<V : BaseViewContract<*>> : BasePresenterContract<V> {

    protected val error = MutableStateFlow<WeatherException?>(null)

    private var _view: V? = null
    protected val view: V
        get() = _view ?: throw IllegalStateException("")

    override fun attachView(view: V) {
        _view = view
    }

    override fun detachView() {
        _view = null
    }
}