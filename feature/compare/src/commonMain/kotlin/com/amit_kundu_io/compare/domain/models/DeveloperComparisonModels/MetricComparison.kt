/**
 * MetricComparison.kt
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

package com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels


data class MetricComparison(
    val metric: DeveloperMetric,

    val developerARawValue: Double,
    val developerBRawValue: Double,

    val developerAScore: Double,
    val developerBScore: Double,

    val weight: Double,

    val winner: DeveloperWinner,
)
