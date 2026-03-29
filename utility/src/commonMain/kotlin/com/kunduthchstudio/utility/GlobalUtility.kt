package com.kunduthchstudio.utility

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

object GlobalUtility {

     const val CACHE_EXPIRY = 3 * 60 * 60

    @OptIn(ExperimentalTime::class)
    fun getTodayDate(): String {
        return Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .toString()
    }

    @OptIn(ExperimentalTime::class)
    fun getLast7DaysDate(): String {
        val now = Clock.System.now()
        val past = now.minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault())

        return past
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .toString()
    }

    @OptIn(ExperimentalTime::class)
    fun getCurrentYear(): String {
        return Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .year
            .toString()
    }

}