package com.swensonhe.weatherapp.data.usecase.datetime

interface DateTimeUseCase {
    fun getCurrentTime(): String
    fun getTodayDate(): String
    fun getADayAfterTomorrow(): String
}