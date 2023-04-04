package com.swensonhe.weatherapp.data.usecase.datetime

import com.swensonhe.weatherapp.data.usecase.datetime.DateTimeUseCase
import com.swensonhe.weatherapp.data.usecase.datetime.DateTimeUseCaseImpl
import org.junit.Before

import org.junit.Test

class DateTimeUseCaseImplTest {

    private lateinit var datetimeUseCase: DateTimeUseCase

    @Before
    fun setup() {
        datetimeUseCase = DateTimeUseCaseImpl()
    }

    @Test
    fun current_time_matches_the_patter_should_success() {
        val currentTime = datetimeUseCase.getCurrentTime()
        println(currentTime)
    }

    @Test
    fun printTodayDate() {
        println(datetimeUseCase.getTodayDate())
    }

    @Test
    fun printADayAfterTomorrow() {
        println(datetimeUseCase.getADayAfterTomorrow())
    }
}