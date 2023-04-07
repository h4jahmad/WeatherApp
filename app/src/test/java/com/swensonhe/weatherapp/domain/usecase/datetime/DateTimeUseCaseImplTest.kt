package com.swensonhe.weatherapp.domain.usecase.datetime

import com.google.common.truth.Truth.*
import org.junit.Before

import org.junit.Test
import java.util.regex.Pattern

class DateTimeUseCaseImplTest {

    private lateinit var datetimeUseCase: DateTimeUseCaseImpl

    @Before
    fun setup() {
        datetimeUseCase = DateTimeUseCaseImpl()
    }

    @Test
    fun current_time_matches_the_patter_should_succeed() {
        val actualCurrentTime = datetimeUseCase.getCurrentTime()
        val pattern = Pattern.compile("h:mm a")
        assertThat(actualCurrentTime).matches(pattern)
    }

    @Test
    fun today_date_matches_pattern_should_succeed() {
        val actualTodayDate = datetimeUseCase.getTodayDate()
        val constPattern = "(?:^|\\W)Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday(?:\$|\\W)" +
                "," +
                "\\s" +
                "^(?:\\d|[12]\\d|3[01])\$" +
                "\\s" +
                "(?:^|\\W)Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec(?:\$|\\W)" +
                "\\s" +
                "\\d{4}\$"
        val pattern = Pattern.compile(constPattern)
        assertThat(actualTodayDate).matches(pattern)
    }

    @Test
    fun day_after_tomorrow_matches_pattern_should_succeed() {
        val actual = datetimeUseCase.getDayAfterTodayName(2)
        val pattern = Pattern.compile("(?:^|\\W)Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday(?:\$|\\W)")
        assertThat(actual).matches(pattern)
    }

    @Test(expected = IllegalArgumentException::class)
    fun day_after_tomorrow_invalid_days_should_failed() {
        datetimeUseCase.getDayAfterTodayName(-1)
    }
}