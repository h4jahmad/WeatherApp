package com.swensonhe.weatherapp.domain.usecase.weather

import com.swensonhe.common.entities.LocationWeather

interface ForecastUseCase {
    suspend fun getLocationForecast(
        locationName: String,
        days: Int,
    ): LocationWeather
}