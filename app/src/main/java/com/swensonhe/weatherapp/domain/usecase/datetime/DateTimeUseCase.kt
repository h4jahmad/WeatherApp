package com.swensonhe.weatherapp.domain.usecase.datetime

interface DateTimeUseCase {
    fun getCurrentTime(): String
    fun getTodayDate(): String
    fun getADayAfterTomorrow(): String
}