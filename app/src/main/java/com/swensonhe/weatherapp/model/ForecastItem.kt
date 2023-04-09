package com.swensonhe.weatherapp.model

data class ForecastItem(
    val weatherType: WeatherType,
    val minTemperature: Float,
    val maxTemperature: Float,
    val day: String,
)
