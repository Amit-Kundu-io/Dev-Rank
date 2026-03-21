package com.kundutechstudio.theme.Components.Badgecomponents


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderDefault
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.RankBronze
import com.kundutechstudio.theme.ui.RankBronzeGhost
import com.kundutechstudio.theme.ui.RankGold
import com.kundutechstudio.theme.ui.RankGoldGhost
import com.kundutechstudio.theme.ui.RankSilver
import com.kundutechstudio.theme.ui.RankSilverGhost
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPlaceholder


@Composable
fun RankBadge(
    rank: Int,
    modifier: Modifier = Modifier,
) {
    val (bgColor, borderColor, textColor) = when (rank) {
        1 -> Triple(RankGoldGhost, RankGold, RankGold)
        2 -> Triple(RankSilverGhost, RankSilver, RankSilver)
        3 -> Triple(RankBronzeGhost, RankBronze, RankBronze)
        else -> Triple(BgSubtle, BorderDefault, TextPlaceholder)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(CardSize.rankBadgeWidth, CardSize.rankBadgeHeight)
            .background(bgColor, DevRankShapes.extraSmall)
            .border(BorderWidth.default, borderColor, DevRankShapes.extraSmall),
    ) {
        Text(
            text = if (rank <= 99) "$rank" else "99+",
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}







// PREVIEWS

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewRankBadges() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(Spacing.lg),
        ) {
            RankBadge(rank = 1)
            RankBadge(rank = 2)
            RankBadge(rank = 3)
            RankBadge(rank = 4)
            RankBadge(rank = 12)
            RankBadge(rank = 2456)
        }
    }
}
