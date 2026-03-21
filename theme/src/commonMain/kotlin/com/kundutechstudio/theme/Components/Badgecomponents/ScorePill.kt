package com.kundutechstudio.theme.Components.Badgecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted

// ── Score pill — "Score: 9,871" ───────────────────────────────────

/**
 * ScorePill — "Score: 9,871" or "⬡ 2,847" highlighted pill
 */
@Composable
fun ScorePill(
    score: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .clip(PillShape)
            .background(AccentBlueGhost)
            .border(BorderWidth.default, AccentBlue.copy(alpha = 0.35f), PillShape)
            .padding(horizontal = Spacing.md, vertical = 5.dp),
    ) {
        Text(
            text = "Score",
            color = TextMuted,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = score,
            color = AccentBlueLight,
            fontSize = 12.sp,
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
            ScorePill("9,871")
        }
    }
}