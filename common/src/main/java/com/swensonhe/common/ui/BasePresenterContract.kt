package com.swensonhe.common.ui

import com.swensonhe.common.entities.WeatherException
import kotlinx.coroutines.flow.StateFlow

interface BasePresenterContract<V : BaseViewContract<*>> {

    fun attachView(view: V)

    fun detachView()

    fun observeError(): StateFlow<WeatherException?>

    fun start() {

    }
}