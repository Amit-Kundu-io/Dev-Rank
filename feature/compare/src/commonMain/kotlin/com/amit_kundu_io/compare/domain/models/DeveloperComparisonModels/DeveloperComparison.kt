/**
 * DeveloperComparison.kt
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

import com.amit_kundu_io.compare.domain.models.DeveloperScore

data class DeveloperComparison(
    val developerA: DeveloperScore,
    val developerB: DeveloperScore,

    val winner: DeveloperWinner,

    val scoreDifference: Double,

    val metricResults: List<MetricComparison>,
)


