package com.kunduthchstudio.utility

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

object GlobalUtility {
    fun getTodayDate(): String {
        return Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .toString()
    }

    fun getLast7DaysDate(): String {
        val now = Clock.System.now()
        val past = now.minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault())

        return past
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .toString()
    }
}