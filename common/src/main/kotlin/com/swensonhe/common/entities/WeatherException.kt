package com.swensonhe.common.entities

class WeatherException(
    val messageResId: Int? = null,
    override val message: String? = null,
) : Exception(message)