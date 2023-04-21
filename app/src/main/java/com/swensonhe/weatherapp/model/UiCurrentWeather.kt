package com.swensonhe.weatherapp.model

data class UiCurrentWeather(
    val tempF: Double,
    val isDay: Int,
    val condition: UiCondition,
    val windMph: Double,
    val humidity: Int,
)
