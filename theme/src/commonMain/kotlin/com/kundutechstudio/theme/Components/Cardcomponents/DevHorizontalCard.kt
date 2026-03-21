package com.kundutechstudio.theme.Components.Cardcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.RankBadge
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueDark
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle

/**
 * DevHorizontalCard — horizontal scroll developer card
 * Used in "Top Developers", "Top Builders" sections
 */
@Composable
fun DevHorizontalCard(
    initials: String,
    username: String,
    statValue: String,
    statLabel: String,
    avatarColor: Color = AccentBlue,
    rank: Int? = null,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
        modifier = modifier
            .width(CardSize.devCardMinWidth)
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .clickable(onClick = onClick)
            .padding(Spacing.md),
    ) {
        rank?.let { RankBadge(it) }

        DevRankAvatar(
            initials = initials,
            size = AvatarSize.md,
            color = avatarColor,
        )

        Text(
            text = username,
            color = TextPrimary,
            fontSize = 11.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = statValue,
            color = AccentBlueLight,
            fontSize = 13.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
            fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
        )

        Text(
            text = statLabel,
            color = TextSubtle,
            fontSize = 9.sp,
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewDevHorizontalCard() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            DevHorizontalCard("TL", "torvalds", "239K", "followers", AccentBlueDark, rank = 1)
            DevHorizontalCard("DS", "defunkt", "195K", "followers", BgSubtle, rank = 2)
            DevHorizontalCard("SS", "sindresorhus", "92K", "followers", AccentBlue, rank = 5)
        }
    }
}

