package com.swensonhe.weatherapp.model

import com.swensonhe.common.entities.LocationWeather
import com.swensonhe.common.util.LocalDtoMapper
import com.swensonhe.weatherapp.domain.usecase.datetime.DateTimeUseCase
import com.swensonhe.weatherapp.domain.usecase.datetime.ForecastDayType
import java.util.*
import javax.inject.Inject

class LocationWeatherMapper @Inject constructor(
    private val dateTimeUseCase: DateTimeUseCase,
    private val locale: Locale,
) : LocalDtoMapper<LocationWeather, UiForecast> {
    override fun toLocalDto(data: LocationWeather): UiForecast {
        val coordinates = Coordinates(
            lat = data.location.lat,
            lon = data.location.lon
        )
        val location = with(data.location) {
            UiLocation(
                name = name,
                region = region,
                country = country,
                coordinates = coordinates
            )
        }
        val currentCondition = with(data.current.condition) {

            UiCondition(text = text, icon = addHttps(icon))
        }
        val currentWeather = with(data.current) {
            UiCurrentWeather(
                tempF = tempF,
                isDay = isDay == 1,
                condition = currentCondition,
                windMph = windMph,
                humidity = humidity
            )
        }
        val forecastDays = data.forecast.forecastDay.map { forecast ->
            val condition = with(forecast.day.condition) {
                UiCondition(text = text, icon = addHttps(icon))
            }

            val dayLabel: ForecastDayType = dateTimeUseCase.getDayName(
                Date(),
                forecast.date,
                locale
            )
            val forecastDay = UiForecastDay(
                date = forecast.date,
                dayLabel = dayLabel,
                maxtempF = forecast.day.maxtempF,
                mintempF = forecast.day.mintempF,
                condition = condition
            )
            forecastDay
        }

        return UiForecast(
            location = location,
            current = currentWeather,
            forecastDays = forecastDays
        )
    }

    private fun addHttps(url: String): String = "https:$url"
}