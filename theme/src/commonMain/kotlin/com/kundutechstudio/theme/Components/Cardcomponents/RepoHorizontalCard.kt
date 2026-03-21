package com.kundutechstudio.theme.Components.Cardcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.RankBadge
import com.kundutechstudio.theme.Components.Badgecomponents.StarChip
import com.kundutechstudio.theme.Components.Badgecomponents.TrendBadge
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingBadge
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingType
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueDark
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.LangJavaScript
import com.kundutechstudio.theme.ui.LangKotlin
import com.kundutechstudio.theme.ui.LangTypeScript
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle


// DEVRANK — Card Components


/**
 * RepoHorizontalCard — horizontal scroll repo card
 * Used in "Most Starred", "Trending Today" sections
 */
@Composable
fun RepoHorizontalCard(
    repoName: String,
    ownerName: String,
    description: String,
    stars: String,
    language: String,
    langColor: Color,
    rank: Int? = null,
    trendingLabel: String? = null,
    trendingType: TrendingType = TrendingType.HOT,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(CardSize.repoCardMinWidth)
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .clickable(onClick = onClick)
            .padding(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            rank?.let { RankBadge(it) }
            trendingLabel?.let { TrendingBadge(it, trendingType) }
            StarChip(stars)
        }

        Text(
            text = repoName,
            color = TextPrimary,
            fontSize = 13.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = "@$ownerName",
            color = TextSubtle,
            fontSize = 10.sp,
            maxLines = 1,
        )

        Text(
            text = description,
            color = TextMuted,
            fontSize = 10.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 14.sp,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(AvatarShape)
                        .background(langColor)
                )
                Text(text = language, color = TextMuted, fontSize = 10.sp)
            }
        }
    }
}








// Previews

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewRepoHorizontalCard() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            RepoHorizontalCard(
                repoName = "freeCodeCamp",
                ownerName = "freeCodeCamp",
                description = "Open-source codebase and curriculum. Learn to code for free.",
                stars = "383K",
                language = "JavaScript",
                langColor = LangJavaScript,
                rank = 1,
            )
            RepoHorizontalCard(
                repoName = "microsoft/vscode",
                ownerName = "microsoft",
                description = "Visual Studio Code — open source code editor.",
                stars = "162K",
                language = "TypeScript",
                langColor = LangTypeScript,
                trendingLabel = "HOT",
                trendingType = TrendingType.HOT,
            )
        }
    }
}





