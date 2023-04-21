package com.swensonhe.weatherapp.domain.repository

import com.swensonhe.common.di.RemoteDataSource
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.LocationWeather
import com.swensonhe.weatherapp.domain.datasource.ForecastDataSource
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    @RemoteDataSource private val remoteDataSource: ForecastDataSource,
) : ForecastRepository {
    override suspend fun getLocationForecast(
        locationLatitude: Double,
        locationLongitude: Double,
        days: Int
    ): ActionResult<LocationWeather> = remoteDataSource.fetchLocationWeather(
        locationLatitude = locationLatitude,
        locationLongitude = locationLongitude,
        days = days
    )

}