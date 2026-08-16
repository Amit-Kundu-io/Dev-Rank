/**
 * DeveloperHeaderComparison.kt
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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperComparison

@Composable
 fun DeveloperHeaderComparison(
    comparison: DeveloperComparison,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        DeveloperScoreCard(
            modifier = Modifier.weight(1f),
            username = comparison.developerA.username,
            avatarUrl = comparison.developerA.avatarUrl,
            score = comparison.developerA.totalScore,
            grade = comparison.developerA.grade,
        )

        Text(
            text = "VS",
            modifier = Modifier.align(Alignment.CenterVertically),
            fontWeight = FontWeight.Bold
        )

        DeveloperScoreCard(
            modifier = Modifier.weight(1f),
            username = comparison.developerB.username,
            avatarUrl = comparison.developerB.avatarUrl,
            score = comparison.developerB.totalScore,
            grade = comparison.developerB.grade,
        )
    }
}