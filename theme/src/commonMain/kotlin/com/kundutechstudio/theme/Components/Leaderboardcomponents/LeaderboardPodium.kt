package com.kundutechstudio.theme.Components.Leaderboardcomponents


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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.RankBadge
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.ui.AccentBlueDark
import com.kundutechstudio.theme.ui.AccentRed
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PodiumBaseShape
import com.kundutechstudio.theme.ui.RankBronze
import com.kundutechstudio.theme.ui.RankBronzeGhost
import com.kundutechstudio.theme.ui.RankGold
import com.kundutechstudio.theme.ui.RankGoldGhost
import com.kundutechstudio.theme.ui.RankSilver
import com.kundutechstudio.theme.ui.RankSilverGhost
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPrimary

/**
 * Podium — Top 3 gold/silver/bronze podium display
 */


@Composable
fun LeaderboardPodium(
    first: PodiumEntry,
    second: PodiumEntry,
    third: PodiumEntry,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.xl, vertical = Spacing.lg),
    ) {
        // #2 — Silver
        PodiumColumn(
            entry = second,
            rank = 2,
            baseHeight = CardSize.podiumBase2Height,
            baseColor = RankSilverGhost,
            baseBorder = RankSilver,
            textColor = RankSilver,
            showCrown = false,
            avatarSize = AvatarSize.md,
            modifier = Modifier.weight(1f).padding(bottom = Spacing.lg),
        )

        Spacer(Modifier.width(Spacing.sm))

        // #1 — Gold (tallest / center)
        PodiumColumn(
            entry = first,
            rank = 1,
            baseHeight = CardSize.podiumBase1Height,
            baseColor = RankGoldGhost,
            baseBorder = RankGold,
            textColor = RankGold,
            showCrown = true,
            avatarSize = AvatarSize.lg,
            modifier = Modifier.weight(1f),
        )

        Spacer(Modifier.width(Spacing.sm))

        // #3 — Bronze
        PodiumColumn(
            entry = third,
            rank = 3,
            baseHeight = CardSize.podiumBase3Height,
            baseColor = RankBronzeGhost,
            baseBorder = RankBronze,
            textColor = RankBronze,
            showCrown = false,
            avatarSize = AvatarSize.sm,
            modifier = Modifier.weight(1f).padding(bottom = Spacing.xxl),
        )
    }
}

@Composable
private fun PodiumColumn(
    entry: PodiumEntry,
    rank: Int,
    baseHeight: Dp,
    baseColor: Color,
    baseBorder: Color,
    textColor: Color,
    showCrown: Boolean,
    avatarSize: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
        modifier = modifier,
    ) {
        if (showCrown) {
            Text("👑", fontSize = 20.sp)
        }

        DevRankAvatar(
            initials = entry.initials,
            size = avatarSize,
            color = entry.avatarColor,
            borderColor = baseBorder,
            borderWidth = BorderWidth.thick,
        )

        Text(
            text = entry.username,
            color = TextPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
        )

        Text(
            text = entry.score,
            color = textColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
        )

        RankBadge(rank)

        // Podium base
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(baseHeight)
                .clip(PodiumBaseShape)
                .background(baseColor)
                .border(BorderWidth.default, baseBorder, PodiumBaseShape)
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewPodium() {
    DevRankTheme {
        LeaderboardPodium(
            first = PodiumEntry("TL", "torvalds", "9,871", AccentBlueDark),
            second = PodiumEntry("DS", "defunkt", "8,234", BgSubtle),
            third = PodiumEntry("MR", "mojombo", "7,612", AccentRed.copy(0.6f)),
        )
    }
}



