package com.kundutechstudio.theme.Components.Comparecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AccentGreenGhost
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextSubtle


/**
 * WinnerBanner — bottom result banner "Winner by Dev Score"
 */
@Composable
fun WinnerBanner(
    winnerName: String,
    scoreDiff: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(DevRankShapes.medium)
            .background(AccentGreenGhost)
            .border(BorderWidth.default, AccentGreen.copy(0.3f), DevRankShapes.medium)
            .padding(Spacing.md),
    ) {
        Text(
            text = "Winner by Dev Score",
            color = TextMuted,
            lineHeight = 16.sp,
            fontSize = 15.sp,
        )
        Text(
            text = "$winnerName 🏆",
            color = AccentGreen,
            lineHeight = 21.sp,
            fontSize = 19.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Text(
            text = "Score difference: +$scoreDiff pts",
            color = TextSubtle,
            fontSize = 13.sp,
            lineHeight = 15.sp,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewWinnerBanner() {
    DevRankTheme {
        WinnerBanner(
            winnerName = "torvalds",
            scoreDiff = "7,024",
            modifier = Modifier.padding(Spacing.lg),
        )
    }
}




