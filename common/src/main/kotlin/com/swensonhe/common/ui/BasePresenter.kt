package com.swensonhe.common.ui

abstract class BasePresenter<V : BaseViewContract<*>> : BasePresenterContract<V> {
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