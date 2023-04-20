package com.swensonhe.weatherapp.model

data class UiCurrentWeather(
    val lastUpdated: String,
    val tempF: Double,
    val isDay: Int,
    val condition: UiCondition,
    val windMph: Double,
    val humidity: Int,
)
