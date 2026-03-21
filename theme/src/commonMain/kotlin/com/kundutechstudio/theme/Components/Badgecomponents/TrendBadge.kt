package com.kundutechstudio.theme.Components.Badgecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AccentGreenGhost
import com.kundutechstudio.theme.ui.AccentRed
import com.kundutechstudio.theme.ui.AccentRedGhost
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing


/**
 * TrendBadge — "▲ +22" green or "▼ -5" red trend indicator
 */
@Composable
fun TrendBadge(
    delta: String,
    isUp: Boolean,
    modifier: Modifier = Modifier,
) {
    val color     = if (isUp) AccentGreen else AccentRed
    val ghostColor = if (isUp) AccentGreenGhost else AccentRedGhost
    val arrow     = if (isUp) "▲" else "▼"

    Box(
        modifier = modifier
            .clip(PillShape)
            .background(ghostColor)
            .padding(horizontal = Spacing.sm, vertical = 3.dp),
    ) {
        Text(
            text       = "$arrow $delta",
            color      = color,
            fontSize   = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewScorePills() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                TrendBadge("+22", isUp = true)
                TrendBadge("-5",  isUp = false)
            }
        }
    }
}

