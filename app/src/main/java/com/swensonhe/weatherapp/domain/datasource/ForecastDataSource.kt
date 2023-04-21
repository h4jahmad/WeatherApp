package com.swensonhe.weatherapp.domain.datasource

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.LocationWeather

interface ForecastDataSource {
    suspend fun fetchLocationWeather(
        locationLatitude: Double,
        locationLongitude: Double,
        days: Int
    ): ActionResult<LocationWeather>
}