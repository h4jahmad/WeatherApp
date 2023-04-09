package com.swensonhe.weatherapp.domain.usecase.weather

import com.swensonhe.common.di.IoDispatcher
import com.swensonhe.common.entities.LocationWeather
import com.swensonhe.weatherapp.domain.repository.ForecastRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastUseCaseImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val forecastRepository: ForecastRepository
) : ForecastUseCase {
    override suspend fun getLocationForecast(
        locationName: String,
        days: Int
    ): LocationWeather = withContext(dispatcher) {
        return@withContext forecastRepository.getLocationForecast(locationName, days)
    }

}