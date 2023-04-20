package com.swensonhe.weatherapp.model

data class UiForecast(
    val location: UiLocation,
    val current: UiCurrentWeather,
    val forecastDays: List<UiForecastDay>,
)
