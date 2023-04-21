package com.swensonhe.weatherapp.domain.usecase.weather

import com.swensonhe.common.di.IoDispatcher
import com.swensonhe.common.entities.ActionResult
import com.swensonhe.common.entities.LocationWeather
import com.swensonhe.common.util.LocalDtoMapper
import com.swensonhe.weatherapp.domain.repository.ForecastRepository
import com.swensonhe.weatherapp.model.UiForecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastUseCaseImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val forecastRepository: ForecastRepository,
    private val mapper: LocalDtoMapper<LocationWeather, UiForecast>
) : ForecastUseCase {
    override suspend fun getLocationForecast(
        locationLatitude: Double,
        locationLongitude: Double,
        days: Int
    ): ActionResult<UiForecast> = withContext(dispatcher) {
        when (val res = forecastRepository.getLocationForecast(
            locationLatitude,
            locationLongitude,
            days
        )) {
            is ActionResult.Error -> ActionResult.Error(res.e)
            is ActionResult.Failure -> ActionResult.Failure(res.e)
            is ActionResult.Success -> ActionResult.Success(mapper.toLocalDto(res.data))
        }
    }

}