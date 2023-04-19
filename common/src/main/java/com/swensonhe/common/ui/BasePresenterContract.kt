package com.swensonhe.common.ui

import com.swensonhe.common.entities.WeatherException
import kotlinx.coroutines.flow.StateFlow

interface BasePresenterContract<V : BaseViewContract<*>> {
    val error: StateFlow<WeatherException?>
    fun attachView(view: V)

    fun detachView()

    fun updateError(exception: WeatherException?)

    fun start() {

    }
}