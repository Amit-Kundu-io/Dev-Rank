/**
 * formatMetricValue.kt
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

package com.amit_kundu_io.compare.presentation.componeants

 fun formatMetricValue(value: Double): String {
    return when {
        value >= 1_000_000 -> { "${formatDecimal(value / 1_000_000)}M"
        }

        value >= 1_000 -> { "${formatDecimal(value / 1_000)}K"
        }

        value % 1.0 == 0.0 -> { value.toLong().toString()
        }

        else -> { formatDecimal(value)
        }
    }
}

 fun formatDecimal(value: Double): String {
    val rounded = kotlin.math.round(value * 10) / 10

    return if (rounded % 1.0 == 0.0) {
        rounded.toLong().toString()
    } else {
        rounded.toString()
    }
}