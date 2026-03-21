package com.kundutechstudio.theme.Components.Badgecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.kundutechstudio.theme.ui.AccentRed
import com.kundutechstudio.theme.ui.AccentRedGhost
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing

/**
 * TrendingBadge — "🔥 Hot", "📈 Rising", "📱 Android" section labels
 */
enum class TrendingType { HOT, RISING, NEW, ANDROID, BEGINNER }

@Composable
fun TrendingBadge(
    label: String,
    type: TrendingType = TrendingType.HOT,
    modifier: Modifier = Modifier,
) {
    val (bg, textColor) = when (type) {
        TrendingType.HOT -> AccentRedGhost to AccentRed
        TrendingType.RISING -> AccentGreenGhost to AccentGreen
        TrendingType.NEW -> AccentGreenGhost to AccentGreen
        TrendingType.ANDROID -> AccentBlueGhost to AccentBlueLight
        TrendingType.BEGINNER -> AccentGreenGhost to AccentGreen
    }

    Box(
        modifier = modifier
            .clip(PillShape)
            .background(bg)
            .border(BorderWidth.thin, textColor.copy(alpha = 0.3f), PillShape)
            .padding(horizontal = Spacing.sm, vertical = 3.dp),
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 9.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.06.sp,
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
                TrendingBadge("HOT", TrendingType.HOT)
                TrendingBadge("RISING", TrendingType.RISING)
                TrendingBadge("ANDROID", TrendingType.ANDROID)
                TrendingBadge("BEGINNER", TrendingType.BEGINNER)
            }

        }
    }
}
