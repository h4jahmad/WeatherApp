package com.swensonhe.common.ui

interface BasePresenterContract<V : BaseViewContract<*>> {

    fun attachView(view: V)

    fun detachView()
}