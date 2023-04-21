package com.swensonhe.weatherapp.domain.datasource

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.LocationWeather
import com.swensonhe.common.service.WeatherService
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(
    private val service: WeatherService,
) : ForecastDataSource {
    override suspend fun fetchLocationWeather(
        locationLatitude: Double,
        locationLongitude: Double,
        days: Int
    ): ActionResult<LocationWeather> = service.getForecast(
        query = "$locationLatitude, $locationLongitude",
        days = days
    )

}