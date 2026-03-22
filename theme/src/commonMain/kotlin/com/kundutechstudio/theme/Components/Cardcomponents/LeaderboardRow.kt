package com.kundutechstudio.theme.Components.Cardcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.RankBadge
import com.kundutechstudio.theme.Components.Badgecomponents.TrendBadge
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle

/**
 * LeaderboardRow — vertical list row for leaderboard screens #4 onwards
 * Top 3 use the Podium component instead
 */
@Composable
fun LeaderboardRow(
    rank: Int,
    initials: String,
    username: String,
    subtitle: String,
    statValue: String,
    statLabel: String,
    delta: String? = null,
    isDeltaUp: Boolean = true,
    avatarColor: Color = AccentBlue,
    isMe: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val bgColor = if (isMe) BgInset else BgOverlay
    val borderColor = if (isMe) AccentBlue.copy(alpha = 0.5f) else BorderMuted

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        modifier = modifier
            .fillMaxWidth()
            .clip(DevRankShapes.medium)
            .background(bgColor)
            .border(BorderWidth.default, borderColor, DevRankShapes.medium)
            .clickable(onClick = onClick)
            .padding(Spacing.md),
    ) {
        RankBadge(rank)

        DevRankAvatar(initials = initials, size = AvatarSize.sm, color = avatarColor)

        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            ) {
                Text(
                    text = username,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                )
                if (isMe) {
                    Box(
                        modifier = Modifier
                            .clip(PillShape)
                            .background(AccentBlueGhost)
                            .border(BorderWidth.thin, AccentBlue.copy(0.4f), PillShape)
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                    ) {
                        Text(
                            text = "YOU",
                            color = AccentBlueLight,
                            fontSize = 8.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                            letterSpacing = 0.08.sp,
                        )
                    }
                }
            }
            Text(text = subtitle, color = TextSubtle, fontSize = 11.sp)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = statValue,
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
            )
            Text(text = statLabel, color = TextSubtle, fontSize = 9.sp)
            delta?.let { TrendBadge(it, isDeltaUp) }
        }
    }
}




@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewLeaderboardRows() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            LeaderboardRow(
                1,
                "PH",
                "pjhyett",
                "GitHub co-founder",
                "128K",
                "followers",
                "+842",
                true
            )
            LeaderboardRow(
                2,
                "PH",
                "pjhyett",
                "GitHub co-founder",
                "128K",
                "followers",
                "+842",
                true
            )
            LeaderboardRow(
                3,
                "PH",
                "pjhyett",
                "GitHub co-founder",
                "128K",
                "followers",
                "+842",
                true
            )
            LeaderboardRow(
                4,
                "SS",
                "sindresorhus",
                "Node.js · OSS",
                "92K",
                "followers",
                "+1.2K",
                true
            )
            LeaderboardRow(
                12,
                "AK",
                "arjun-kapoor",
                "Sr. Eng · Google",
                "3.2K",
                "followers",
                "+22",
                true,
                isMe = true
            )
            LeaderboardRow(
                999,
                "AK",
                "arjun-kapoor",
                "Sr. Eng · Google",
                "3.2K",
                "followers",
                "+22",
                true,
                isMe = true
            )

            LeaderboardRow(
                999,
                "AK",
                "arjun-kapoor",
                "Sr. Eng · Google",
                "3.2K",
                "followers",
                delta = null,
                true,
                isMe = true
            )
        }
    }
}

