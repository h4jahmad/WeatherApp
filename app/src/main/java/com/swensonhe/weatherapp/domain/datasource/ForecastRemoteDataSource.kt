package com.swensonhe.weatherapp.domain.datasource

import com.swensonhe.common.entities.LocationWeather
import com.swensonhe.common.service.WeatherService
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(
    private val service: WeatherService
): ForecastDataSource {
    override suspend fun fetchLocationWeather(locationName: String, days: Int): LocationWeather =
        service.getForecast(locationName, days)

}