/**
 * WinnerCard.kt
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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperComparison
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperWinner
import com.kundutechstudio.theme.Components.Comparecomponents.WinnerBanner
import com.kunduthchstudio.utility.GlobalUtility.roundTo

@Composable
fun WinnerCard(
    comparison: DeveloperComparison,
) {
    val winnerName = when (comparison.winner) {

        DeveloperWinner.DEVELOPER_A -> comparison.developerA.username

        DeveloperWinner.DEVELOPER_B -> comparison.developerB.username

        DeveloperWinner.DRAW -> "It's a Draw"
    }

    val result = remember(comparison) {
        val developerA = comparison.developerA
        val developerB = comparison.developerB

        when {
            developerA.totalScore > developerB.totalScore -> {
                val difference = developerA.totalScore - developerB.totalScore

                ScoreDifferenceResult(
                    winner = developerA.username,
                    difference = difference,
                )
            }

            developerB.totalScore > developerA.totalScore -> {
                val difference = developerB.totalScore - developerA.totalScore

                ScoreDifferenceResult(
                    winner = developerB.username,
                    difference = difference,
                )
            }

            else -> {
                ScoreDifferenceResult(
                    winner = null,
                    difference = 0.0,
                )
            }
        }
    }

    WinnerBanner(
        winnerName = winnerName,
        scoreDiff = result.winner?.let { result.difference.roundTo(2) } ?: "0" ,
    )

}

private data class ScoreDifferenceResult(
    val winner: String?,
    val difference: Double,
)

