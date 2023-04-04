package com.swensonhe.common.entities

import retrofit2.Response

sealed class ActionResult<out T> {
    data class Success<T>(val data: Response<T>) : ActionResult<T>()
    data class Error(val e: WeatherException) : ActionResult<Nothing>()
    data class Failure(val e: Throwable) : ActionResult<Nothing>()
}