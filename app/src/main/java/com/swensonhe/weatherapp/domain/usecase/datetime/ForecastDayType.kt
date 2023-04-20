package com.swensonhe.weatherapp.domain.usecase.datetime

sealed interface ForecastDayType {
    object Today : ForecastDayType
    object Tomorrow : ForecastDayType
    data class Name(val name: String) : ForecastDayType
}