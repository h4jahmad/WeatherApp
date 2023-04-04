package com.swensonhe.weatherapp.data.usecase

interface DateTimeUseCase {
    fun getCurrentTime(): String
    fun getTodayDate(): String
    fun getADayAfterTomorrow(): String
}