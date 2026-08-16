/**
 * MetricComparisonCard.kt
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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperMetric
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.MetricComparison

@Composable
 fun MetricComparisonCard(
    metric: MetricComparison,
    developerA: String,
    developerB: String,
) {

    val title =
        when (metric.metric) {

            DeveloperMetric.STARS -> {
                "⭐ Repository Stars"
            }

            DeveloperMetric.FORKS -> {
                "🔀 Forks"
            }

            DeveloperMetric.REPOSITORY_QUALITY -> {
                "💻 Repository Quality"
            }

            DeveloperMetric.CONTRIBUTIONS -> {
                "🔥 Contributions"
            }

            DeveloperMetric.ISSUES_PULL_REQUESTS -> {
                "🐛 Issues / Pull Requests"
            }

            DeveloperMetric.FOLLOWERS -> {
                "👥 Followers"
            }

            DeveloperMetric.PR_REVIEWS -> {
                "📝 PR Reviews"
            }

            DeveloperMetric.ACTIVITY -> {
                "🕐 Activity / Consistency"
            }
        }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            DeveloperMetricRow(
                username = developerA,
                rawValue = metric.developerARawValue,
                score = metric.developerAScore
            )

            Spacer(Modifier.height(8.dp))

            DeveloperMetricRow(
                username = developerB,
                rawValue = metric.developerBRawValue,
                score = metric.developerBScore
            )

            Spacer(
                Modifier.height(8.dp)
            )

            Text(
                text = "Weight: ${(metric.weight * 100).toInt()}%",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

