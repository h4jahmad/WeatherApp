package com.swensonhe.weatherapp.domain.usecase.weather

import com.swensonhe.common.entities.ActionResult
import com.swensonhe.weatherapp.model.UiForecast

interface ForecastUseCase {
    suspend fun getLocationForecast(
        locationLatitude: Double,
        locationLongitude: Double,
        days: Int,
    ): ActionResult<UiForecast>
}