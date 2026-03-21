package com.kundutechstudio.theme.Components.Leaderboardcomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.SectionBadge
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AccentGreenGhost
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.StarYellow
import com.kundutechstudio.theme.ui.StarYellowGhost
import com.kundutechstudio.theme.ui.TextPrimary

/**
 * SectionHeader — section title + optional badge + "View All" link
 */
@Composable
fun SectionHeader(
    title: String,
    badgeLabel: String? = null,
    badgeColor: Color = AccentBlueLight,
    badgeBg: Color = AccentBlueGhost,
    onViewAll: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier              = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.xl, vertical = Spacing.sm),
    ) {
        Row(
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            Text(
                text       = title,
                color      = TextPrimary,
                fontSize   = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                style      = MaterialTheme.typography.headlineSmall,
            )
            badgeLabel?.let {
                SectionBadge(it, badgeColor, badgeBg)
            }
        }
        onViewAll?.let {
            Text(
                text       = "All →",
                color      = AccentBlueLight,
                fontSize   = 11.sp,
                fontWeight = FontWeight.SemiBold,
                modifier   = Modifier.clickable(onClick = it),
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewSectionHeaders() {
    DevRankTheme {
        Column {
            SectionHeader("⭐ Most Starred", "REPOS", StarYellow, StarYellowGhost, onViewAll = {})
            SectionHeader("👤 Top Developers", "LIVE",  AccentGreen, AccentGreenGhost, onViewAll = {})
            SectionHeader("🔥 Trending Today",  null, onViewAll = {})
        }
    }
}

