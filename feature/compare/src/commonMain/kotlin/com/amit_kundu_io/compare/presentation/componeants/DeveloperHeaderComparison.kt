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


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperComparison
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperWinner
import com.amit_kundu_io.compare.domain.models.DeveloperScore
import com.kundutechstudio.theme.Components.Comparecomponents.VsBadge
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.Helpers.Helpers
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AccentGreenGhost
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle



/**
 * DeveloperHeaderComparison — top "A vs B" header on the Compare screen.
 * Two DeveloperScoreCards either side of a VsBadge, with the score
 * difference shown underneath it.
 */
@Composable
fun DeveloperHeaderComparison(
    comparison: DeveloperComparison,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        ) {
            DeveloperScoreCard(
                modifier = Modifier.weight(1f),
                developer = comparison.developerA,
                isWinner = comparison.winner == DeveloperWinner.DEVELOPER_A,
                avatarColor = AccentBlue,
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                VsBadge()
                if (comparison.winner != DeveloperWinner.DRAW) {
                    Text(
                        text = "Δ ${Helpers.formatNumber(comparison.scoreDifference.toInt())}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSubtle,
                        ),
                    )
                }
            }

            DeveloperScoreCard(
                modifier = Modifier.weight(1f),
                developer = comparison.developerB,
                isWinner = comparison.winner == DeveloperWinner.DEVELOPER_B,
                avatarColor = AccentGreen,
            )
        }
    }
}

/**
 * DeveloperScoreCard — one side of the header comparison: avatar, username,
 * total score, and grade. Highlighted with the accent border when this
 * developer won the overall comparison.
 *
 * NOTE: `avatarUrl` is accepted for API compatibility with DeveloperScore,
 * but DevRankAvatar in this theme only renders initials — there's no
 * async image loader (Coil/Kamel) wired up in the theme module. Swap in
 * an image-backed avatar here once one is added to the project.
 */
@Composable
fun DeveloperScoreCard(
    developer: DeveloperScore,
    isWinner: Boolean,
    avatarColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
) {
    val borderColor = if (isWinner) AccentGreen else BorderMuted
    val bgColor = if (isWinner) AccentGreenGhost else BgOverlay

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(DevRankShapes.large)
            .background(bgColor)
            .border(BorderWidth.default, borderColor, DevRankShapes.large)
            .padding(Spacing.md),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isWinner) {
            Text(
                text = "👑",
                fontSize = 9.sp,
                lineHeight = 10.sp,
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
            )
        }

        Spacer(modifier = Modifier.height(Spacing.sm))

        DevRankAvatar(
            initials = developer.username,
            size = AvatarSize.lg,
            color = avatarColor,
            borderColor = if (isWinner) AccentGreen else null,
            borderWidth = BorderWidth.thick,
        )
        Spacer(modifier = Modifier.height(Spacing.sm))

        Text(
            text = "@${developer.username}",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(Spacing.sm))

        Text(
            text = Helpers.formatScore(developer.totalScore.toInt()),
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (isWinner) AccentGreen else AccentBlueLight,
            ),
        )
        Spacer(modifier = Modifier.height(Spacing.sm))

        Box(
            modifier = Modifier
                .clip(DevRankShapes.extraSmall)
                .background(if (isWinner) AccentGreenGhost else BgOverlay)
                .border(
                    BorderWidth.default,
                    (if (isWinner) AccentGreen else TextPrimary).copy(alpha = 0.35f),
                    DevRankShapes.extraSmall,
                )
                .padding(horizontal = Spacing.md, vertical = 3.dp),
        ) {
            Text(
                text = developer.grade,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isWinner) AccentGreen else TextPrimary,
                ),
            )
        }
    }
}

