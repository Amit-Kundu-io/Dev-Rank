package com.kundutechstudio.theme.Components.Statcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.StarYellow
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle


/**
 * StatGridCard — single stat cell used in Repo Detail 3x2 grid
 */
@Composable
fun StatGridCard(
    value: String,
    label: String,
    valueColor: Color = TextPrimary,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .padding(Spacing.md),
    ) {
        Text(
            text = value,
            color = valueColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = label,
            color = TextSubtle,
            fontSize = 9.sp,
            letterSpacing = 0.05.sp,
        )
    }
}


// ─────────────────────────────────────────────────────────────────
// Previews
// ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewStatGrid() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            StatGridCard("383K", "⭐ Stars", StarYellow, Modifier.weight(1f))
            StatGridCard("33K", "🍴 Forks", AccentBlueLight, Modifier.weight(1f))
            StatGridCard("6.2K", "👁 Watch", AccentGreen, Modifier.weight(1f))
        }
    }
}



