package com.swensonhe.weatherapp.domain.usecase.datetime

import java.util.*

interface DateTimeUseCase {
    fun getCurrentTime(locale: Locale): String
    fun getTodayDate(locale: Locale): String
    fun getDayName(
        baseDate: Date,
        otherDate: String,
        locale: Locale,
    ): ForecastDayType
}