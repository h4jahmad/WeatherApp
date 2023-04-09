package com.swensonhe.common.entities

data class LocationWeather(
    val location: ForecastLocation,
    val current: Current,
    val forecast: Forecast,
)