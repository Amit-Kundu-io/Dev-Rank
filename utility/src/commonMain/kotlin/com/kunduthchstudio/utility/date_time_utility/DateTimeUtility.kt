/**
 * DateTimeUtility.kt
 *
 * Author      : Amit Kundu
 * Created On  : 16/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.kunduthchstudio.utility.date_time_utility

import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

object DateTimeUtility {

    fun currentYearDateRange(): Pair<String, String> {
        val today = Clock.System.now()
            .toLocalDateTime(TimeZone.UTC)
            .date

        val year = today.year

        val from = "${year}-01-01T00:00:00Z"

        val to = "${year}-" +
                today.month.number.toString().padStart(2, '0') +
                "-" +
                today.day.toString().padStart(2, '0') +
                "T23:59:59Z"

        return from to to
    }

}