package com.kundutechstudio.theme.Components.Comparecomponents

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.RankBadge
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueDark
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
 * UserComparePanel — one side of the VS compare layout
 */
@Composable
fun UserComparePanel(
    initials: String,
    username: String,
    rank: Int,
    avatarColor: Color,
    stats: List<CompareStatRow>,
    isWinner: Boolean,
    modifier: Modifier = Modifier,
    isLeft: Boolean = true,
) {
    val borderColor = if (isWinner) AccentGreen else BorderMuted
    val bgColor = if (isWinner) AccentGreenGhost else BgOverlay

    Column(
        modifier = modifier
            .clip(DevRankShapes.large)
            .background(bgColor)
            .border(BorderWidth.default, borderColor, DevRankShapes.large)
            .padding(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
    ) {
        // Winner crown
        if (isWinner) {
            Text(
                text = "👑",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        } else {
            Spacer(Modifier.height(28.dp))
        }

        // Avatar + name
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            DevRankAvatar(
                initials = initials,
                size = AvatarSize.lg,
                color = avatarColor,
                borderColor = if (isWinner) AccentGreen else null,
                borderWidth = BorderWidth.thick,
            )
            Spacer(Modifier.height(Spacing.sm))
            Text(
                text = username,
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            RankBadge(rank)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(BorderWidth.default)
                .background(borderColor.copy(0.3f))
        )

        // Stats
        stats.forEach { row ->
            val myValue = if (isLeft) row.valueA else row.valueB
            val iWin = if (isLeft) row.winnerIsA else !row.winnerIsA
            val textColor = if (iWin) AccentGreen else TextPrimary

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = row.label,
                    color = TextSubtle,
                    fontSize = 9.sp,
                    lineHeight = 12.sp,
                    letterSpacing = 0.06.sp,
                )
                Text(
                    text = myValue,
                    color = textColor,
                    fontSize = 13.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                )
            }
        }
    }
}

private val sampleStats = listOf(
    CompareStatRow("SCORE", "9,871", "2,847", winnerIsA = true),
    CompareStatRow("FOLLOWERS", "239K", "3.2K", winnerIsA = true),
    CompareStatRow("STARS", "842K", "24K", winnerIsA = true),
    CompareStatRow("REPOS", "11", "128", winnerIsA = false),
    CompareStatRow("COMMITS", "41.2K", "2.4K", winnerIsA = true),
)


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewComparePanel() {
    DevRankTheme {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            UserComparePanel(
                initials = "TL",
                username = "torvalds",
                rank = 1,
                avatarColor = AccentBlueDark,
                stats = sampleStats,
                isWinner = true,
                isLeft = true,
                modifier = Modifier.weight(1f),
            )
            UserComparePanel(
                initials = "AK",
                username ="amit-kundu-io",
                rank = 2456,
                avatarColor = AccentBlue,
                stats = sampleStats,
                isWinner = false,
                isLeft = false,
                modifier = Modifier.weight(1f),
            )
        }
    }
}



