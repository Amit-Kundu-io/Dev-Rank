package com.kundutechstudio.theme.Components.Badgecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AccentGreenGhost
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.StarYellow
import com.kundutechstudio.theme.ui.StarYellowGhost

/**
 * SectionBadge — small colored label next to section titles
 * e.g. "LIVE", "REPOS", "DEVS"
 */
@Composable
fun SectionBadge(
    label: String,
    color: androidx.compose.ui.graphics.Color = AccentBlueLight,
    bgColor: androidx.compose.ui.graphics.Color = AccentBlueGhost,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(PillShape)
            .background(bgColor)
            .padding(horizontal = Spacing.sm, vertical = 2.dp),
    ) {
        Text(
            text = label,
            color = color,
            fontSize = 9.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.1.sp,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewPillsAndChips() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                SectionBadge("LIVE")
                SectionBadge("REPOS", StarYellow, StarYellowGhost)
                SectionBadge("DEVS", AccentGreen, AccentGreenGhost)
            }
        }
    }
}


