package com.swensonhe.weatherapp.model

import com.swensonhe.weatherapp.domain.usecase.datetime.ForecastDayType

data class UiForecastDay(
    val date: String,
    val dayLabel: ForecastDayType,
    val maxtempF: Double,
    val mintempF: Double,
    val condition: UiCondition,
)
