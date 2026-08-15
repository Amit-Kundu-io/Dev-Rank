package com.kundutechstudio.theme.Components.Cardcomponents.RepoVerticalCard


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
import com.kundutechstudio.theme.Components.Badgecomponents.StarChip
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingBadge
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingType
import com.kundutechstudio.theme.Helpers.Helpers
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPrimary

@Composable
fun RepoVerticalCard(
    name: String,
    description: String,
    stars: String,
    language: String,
    trendingLabel: String? = null,
    trendingType: TrendingType = TrendingType.HOT,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            //.height(135.dp)
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .clickable(onClick = onClick)
            .padding(Spacing.md),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        // ── Row 1: name + badge ───────────────────────────────────
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = name,
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            trendingLabel?.let {
                Spacer(Modifier.width(Spacing.sm))
                TrendingBadge(it, trendingType)
            }
        }

        // ── Description ───────────────────────────────────────────

        Spacer(modifier = Modifier.height(8.dp))

        if (!description.isBlank()){
            Text(
                text = description,
                color = TextMuted,
                fontSize = 11.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))

        }


        // ── Row 3: stars + language ───────────────────────────────
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        ) {
            StarChip(stars)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(AvatarShape)
                        .background(Helpers.getLanguageColor(language))
                )
                Text(text = language, color = TextMuted, fontSize = 10.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RepoVerticalCardPreview() {
    DevRankTheme {
        Column {
            RepoVerticalCard(
                name = "Dev-Rank",
                description = "A developer ranking platform built with Kotlin Multiplatform and GitHub integration.",
                stars = "128",
                language = "Kotlin",
                trendingLabel = "Trending",
                trendingType = TrendingType.PUBLIC,
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
            RepoVerticalCard(
                name = "Dev-Rank",
                description = "",
                stars = "128",
                language = "Kotlin",
                trendingLabel = "Trending",
                trendingType = TrendingType.PUBLIC,
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )

        }

    }
}