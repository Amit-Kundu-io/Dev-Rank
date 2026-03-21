package com.kundutechstudio.theme.Components.Statcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing

/**
 * ContributionHeatmap — GitHub-style activity grid
 * 26 weeks × 7 days
 */
@Composable
fun ContributionHeatmap(
    levels: List<List<Int>>,   // 26 × 7 grid of values 0-4
    modifier: Modifier = Modifier,
) {
    val cellColors = listOf(
        BgInset,                          // 0 = empty
        Color(0xFF0E4429),                // 1 = light green
        Color(0xFF006D32),                // 2
        Color(0xFF26A641),                // 3
        Color(0xFF39D353),                // 4 = bright green
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(3.dp),
        modifier = modifier,
    ) {
        repeat(7) { dayIndex ->
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                repeat(26) { weekIndex ->
                    val level = levels.getOrNull(weekIndex)?.getOrNull(dayIndex) ?: 0
                    Box(
                        modifier = Modifier
                            .size(CardSize.heatmapCellSize)
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(2.dp))
                            .background(cellColors[level.coerceIn(0, 4)])
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewHeatmap() {
    DevRankTheme {
        val fakeData = List(26) { w ->
            List(7) { d -> if (d < 8) listOf(0, 1, 2, 3, 8).random() else 0 }
        }
        ContributionHeatmap(
            levels = fakeData,
            modifier = Modifier.padding(Spacing.lg),
        )
    }
}



