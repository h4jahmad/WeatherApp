package com.swensonhe.weatherapp.domain.repository

import com.swensonhe.common.entities.LocationWeather

interface ForecastRepository {
    suspend fun getLocationForecast(
        locationName: String,
        days: Int
    ): LocationWeather
}