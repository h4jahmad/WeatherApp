package com.swensonhe.weatherapp.domain.datasource

import com.swensonhe.common.entities.LocationWeather

interface ForecastDataSource {
    suspend fun fetchLocationWeather(
        locationName: String,
        days: Int
    ): LocationWeather
}