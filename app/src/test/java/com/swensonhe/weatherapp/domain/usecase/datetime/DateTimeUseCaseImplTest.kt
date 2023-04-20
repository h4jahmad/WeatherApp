package com.swensonhe.weatherapp.domain.usecase.datetime

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class DateTimeUseCaseImplTest {

    private lateinit var datetimeUseCase: DateTimeUseCaseImpl

    private val locale: Locale = Locale.getDefault()
    //2015-1-5 01:01:01
    private val baseDate: Date = SimpleDateFormat("yyyy-MM-dd")
        .parse("2015-01-05")!!

    @Before
    fun setup() {
        datetimeUseCase = DateTimeUseCaseImpl()
    }

    @Test
    fun current_time_matches_the_patter_should_succeed() {
        val actualCurrentTime = datetimeUseCase.getCurrentTime(locale)
        val pattern = Pattern.compile("(?:[1-9]|[1-9][1-9]):\\d\\d\\s(?:AM|PM)")
        assertThat(actualCurrentTime).matches(pattern)
    }

    @Test
    fun today_date_matches_pattern_should_succeed() {
        val actualTodayDate = datetimeUseCase.getTodayDate(locale)
        val pattern = Pattern.compile(
            "(?:Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday),\\s(?:0?[1-9]|1\\d|2\\d|3[01])\\s(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s\\d{4}"
        )
        assertThat(actualTodayDate).matches(pattern)
    }

    @Test(expected = ParseException::class)
    fun get_day_name_pass_past_dates_should_through_exception() {
        datetimeUseCase.getDayName(baseDate, "205-1-4", locale)
        datetimeUseCase.getDayName(baseDate, "2051/1/4", locale)
        datetimeUseCase.getDayName(baseDate, "04-23-2023", locale)
        datetimeUseCase.getDayName(baseDate, "40-40-2023", locale)
    }

    @Test
    fun get_day_name_today_should_succeed() {
        val actual = datetimeUseCase.getDayName(baseDate, "2015-01-05", locale)
        assertThat(actual).isInstanceOf(ForecastDayType.Today::class.java)
    }

    @Test
    fun get_day_name_tomorrow_should_succeed() {
        val actual = datetimeUseCase.getDayName(baseDate, "2015-01-06", locale)
        assertThat(actual).isInstanceOf(ForecastDayType.Tomorrow::class.java)
    }

    @Test
    fun get_day_name__should_succeed() {
        val actual = datetimeUseCase.getDayName(baseDate, "2015-01-07", locale)
        assertThat(actual).isInstanceOf(ForecastDayType.Name::class.java)
        actual as ForecastDayType.Name
        assertThat(actual.name).isEqualTo("Wednesday")
    }

}