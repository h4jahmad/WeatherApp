package com.swensonhe.weatherapp.domain.repository

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.LocationWeather

interface ForecastRepository {
    suspend fun getLocationForecast(
        locationLatitude: Double,
        locationLongitude: Double,
        days: Int
    ): ActionResult<LocationWeather>
}