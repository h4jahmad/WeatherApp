package com.swensonhe.common.entities


sealed class ActionResult<out T> {
    data class Success<T>(val data: T) : ActionResult<T>()
    data class Error(val e: WeatherException) : ActionResult<Nothing>()
    data class Failure(val e: Throwable) : ActionResult<Nothing>()
}