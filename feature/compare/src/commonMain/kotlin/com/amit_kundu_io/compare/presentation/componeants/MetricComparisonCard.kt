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


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperMetric
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperWinner
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.MetricComparison
import com.amit_kundu_io.compare.presentation.componeants.emoji
import com.amit_kundu_io.compare.presentation.componeants.label
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSecondary
import com.kundutechstudio.theme.ui.TextSubtle

import com.kundutechstudio.theme.Helpers.Helpers


private val DeveloperMetric.emoji: String
    get() = when (this) {
        DeveloperMetric.STARS -> "⭐"
        DeveloperMetric.FORKS -> "🔀"
        DeveloperMetric.REPOSITORY_QUALITY -> "💻"
        DeveloperMetric.CONTRIBUTIONS -> "🔥"
        DeveloperMetric.ISSUES_PULL_REQUESTS -> "🐛"
        DeveloperMetric.FOLLOWERS -> "👥"
        DeveloperMetric.PR_REVIEWS -> "📝"
        DeveloperMetric.ACTIVITY -> "🕐"
    }

private val DeveloperMetric.label: String
    get() = when (this) {
        DeveloperMetric.STARS -> "Repository Stars"
        DeveloperMetric.FORKS -> "Forks"
        DeveloperMetric.REPOSITORY_QUALITY -> "Repository Quality"
        DeveloperMetric.CONTRIBUTIONS -> "Contributions"
        DeveloperMetric.ISSUES_PULL_REQUESTS -> "Issues / Pull Requests"
        DeveloperMetric.FOLLOWERS -> "Followers"
        DeveloperMetric.PR_REVIEWS -> "PR Reviews"
        DeveloperMetric.ACTIVITY -> "Activity / Consistency"
    }


/**
 * MetricComparisonCard — one weighted metric row on the Compare screen.
 * Shows the metric title + weight, then each developer's raw value
 * with a proportional score bar underneath.
 */
@Composable
fun MetricComparisonCard(
    metric: MetricComparison,
    developerA: String,
    developerB: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .padding(Spacing.lg),
        verticalArrangement = Arrangement.spacedBy(Spacing.md),
    ) {

        // ── Header: title + weight ──────────────────────────
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "${metric.metric.emoji}  ${metric.metric.label}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "Weight ${(metric.weight * 100).toInt()}%",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSubtle,
                    letterSpacing = 0.05.sp,
                ),
            )
        }

        // Relative share of the two scores, used only to size the bars.
        // Falls back to an even split if both scores are zero.
        val scoreTotal = metric.developerAScore + metric.developerBScore
        val shareA = if (scoreTotal > 0.0) (metric.developerAScore / scoreTotal).toFloat() else 0.5f
        val shareB = if (scoreTotal > 0.0) (metric.developerBScore / scoreTotal).toFloat() else 0.5f

        // ── Both developers ──────────────────────────────────
        DeveloperMetricRow(
            username = developerA,
            rawValue = Helpers.formatNumber(metric.developerARawValue.toInt()),
            barShare = shareA,
            isWinner = metric.winner == DeveloperWinner.DEVELOPER_A,
            barColor = AccentBlueLight,
        )
        DeveloperMetricRow(
            username = developerB,
            rawValue = Helpers.formatNumber(metric.developerBRawValue.toInt()),
            barShare = shareB,
            isWinner = metric.winner == DeveloperWinner.DEVELOPER_B,
            barColor = AccentGreen,
        )
    }
}

// ── DeveloperMetricRow ──────────────────────────────────────────

/**
 * DeveloperMetricRow — username + raw value + proportional score bar.
 * [barShare] is the 0f..1f fraction of the bar this developer fills,
 * already resolved by the caller from the two developers' scores.
 */
@Composable
fun DeveloperMetricRow(
    username: String,
    rawValue: String,
    barShare: Float,
    isWinner: Boolean,
    barColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing.xs),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = username,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = if (isWinner) FontWeight.Bold else FontWeight.Medium,
                    color = if (isWinner) TextPrimary else TextMuted,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
            ) {
                if (isWinner) {
                    Text(
                        text = "▲",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 10.sp,
                            color = AccentGreen,
                        ),
                    )
                }
                Text(
                    text = rawValue,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isWinner) AccentGreen else TextSecondary,
                    ),
                )
            }
        }

        // Proportional bar — visual share of this metric
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(DevRankShapes.extraSmall)
                .background(BgInset),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = barShare.coerceIn(0.04f, 1f))
                    .clip(DevRankShapes.extraSmall)
                    .background(if (isWinner) barColor else barColor.copy(alpha = 0.4f)),
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────
// Preview
// ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewMetricComparisonCard() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            MetricComparisonCard(
                metric = MetricComparison(
                    metric = DeveloperMetric.STARS,
                    developerARawValue = 842_000.0,
                    developerBRawValue = 24_000.0,
                    developerAScore = 970.0,
                    developerBScore = 140.0,
                    weight = 0.25,
                    winner = DeveloperWinner.DEVELOPER_A,
                ),
                developerA = "torvalds",
                developerB = "amit-kundu-io",
            )
            MetricComparisonCard(
                metric = MetricComparison(
                    metric = DeveloperMetric.CONTRIBUTIONS,
                    developerARawValue = 41_200.0,
                    developerBRawValue = 2_400.0,
                    developerAScore = 900.0,
                    developerBScore = 300.0,
                    weight = 0.2,
                    winner = DeveloperWinner.DEVELOPER_A,
                ),
                developerA = "torvalds",
                developerB = "amit-kundu-io",
            )
        }
    }
}