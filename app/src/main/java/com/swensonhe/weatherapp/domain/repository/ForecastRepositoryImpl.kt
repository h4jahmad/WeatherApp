package com.swensonhe.weatherapp.domain.repository

import com.swensonhe.common.di.RemoteDataSource
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.LocationWeather
import com.swensonhe.common.service.ImageService
import com.swensonhe.weatherapp.domain.datasource.ForecastDataSource
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    @RemoteDataSource private val remoteDataSource: ForecastDataSource,
    private val imageService: ImageService,
) : ForecastRepository {
    override suspend fun getLocationForecast(
        locationLatitude: Double,
        locationLongitude: Double,
        days: Int
    ): ActionResult<LocationWeather> = remoteDataSource.fetchLocationWeather(
        locationLatitude = locationLatitude,
        locationLongitude = locationLongitude,
        days = days
    ).also { result ->
        // In this block, icons will be cached in order to prevent extra requests for
        // old images.
        if (result !is ActionResult.Success) return@also
        imageService.enqueue(result.data.current.condition.icon)
        result.data.forecast.forecastDay.forEach { forecast ->
            imageService.enqueue(forecast.day.condition.icon)
        }
    }

}