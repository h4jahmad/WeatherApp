package com.swensonhe.common.ui

import com.swensonhe.common.entities.WeatherException

interface BasePresenterContract<V : BaseViewContract<*>> {
    fun attachView(view: V)

    fun detachView()

    fun updateError(exception: WeatherException?)

    fun start() {

    }
}