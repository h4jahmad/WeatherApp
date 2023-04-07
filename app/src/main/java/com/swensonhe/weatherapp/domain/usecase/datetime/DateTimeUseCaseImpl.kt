package com.swensonhe.weatherapp.domain.usecase.datetime

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUseCaseImpl : DateTimeUseCase {
    private val defaultLocale = Locale.US
    override fun getCurrentTime(): String =
        SimpleDateFormat("h:mm a", defaultLocale).format(Date())

    override fun getTodayDate(): String =
        SimpleDateFormat("EEEE, d MMM yyyy", defaultLocale).format(Date())

    override fun getDayAfterTodayName(dayAfterToday: Int): String {
        if (dayAfterToday <= 0) {
            throw IllegalArgumentException("dayAfterToday should be greater than zero.")
        }
        return SimpleDateFormat("EEEE", defaultLocale)
            .format(
                Calendar.getInstance(defaultLocale).apply {
                    time = Date()
                    add(Calendar.DAY_OF_MONTH, dayAfterToday)
                }.time
            )
    }

}