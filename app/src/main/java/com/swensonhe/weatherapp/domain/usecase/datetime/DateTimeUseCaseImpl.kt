package com.swensonhe.weatherapp.domain.usecase.datetime

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_YEAR
import java.util.Calendar.YEAR
import javax.inject.Inject

class DateTimeUseCaseImpl @Inject constructor() : DateTimeUseCase {

    override fun getCurrentTime(locale: Locale): String =
        SimpleDateFormat("h:mm a", locale).format(Date())

    override fun getTodayDate(locale: Locale): String =
        SimpleDateFormat("EEEE, d MMM yyyy", locale).format(Date())

    override fun getDayName(
        baseDate: Date,
        otherDate: String,
        locale: Locale
    ): ForecastDayType {
        val formatter = SimpleDateFormat("yyyy-MM-dd", locale)
        val otherDateObj: Date = try {
            formatter.parse(otherDate)!!
        } catch (e: ParseException) {
            throw ParseException(
                "Invalid date format. the date should be formatted in yyyy-MM-dd.",
                e.errorOffset
            )
        }

        if (isToday(baseDate, otherDateObj)) return ForecastDayType.Today
        if (isTomorrow(baseDate, otherDateObj)) return ForecastDayType.Tomorrow

        return ForecastDayType.Name(
            SimpleDateFormat(
                "EEEE",
                locale
            ).format(otherDateObj)
        )
    }

    private fun isToday(baseDate: Date, otherDateObj: Date): Boolean {
        val base = Calendar.getInstance()
            .apply { time = baseDate }
        val other = Calendar.getInstance()
            .apply { time = otherDateObj }

        return base.get(DAY_OF_YEAR) == other.get(DAY_OF_YEAR) &&
                base.get(YEAR) == other.get(YEAR)
    }

    private fun isTomorrow(baseDate: Date, otherDateObj: Date): Boolean {
        val tomorrow = Calendar.getInstance().apply {
            time = baseDate
            add(DAY_OF_YEAR, 1)
        }
        val other = Calendar.getInstance()
            .apply { time = otherDateObj }

        return tomorrow.get(DAY_OF_YEAR) == other.get(DAY_OF_YEAR) &&
                tomorrow.get(YEAR) == other.get(YEAR)
    }

}