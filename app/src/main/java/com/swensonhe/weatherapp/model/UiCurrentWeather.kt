package com.swensonhe.weatherapp.model

data class UiCurrentWeather(
    val tempF: Double,
    val isDay: Boolean,
    val condition: UiCondition,
    val windMph: Double,
    val humidity: Int,
)
