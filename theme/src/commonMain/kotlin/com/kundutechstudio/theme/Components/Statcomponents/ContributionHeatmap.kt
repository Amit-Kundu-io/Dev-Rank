package com.kundutechstudio.theme.Components.Statcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPlaceholder

/**
 * ContributionHeatmap — GitHub-style activity grid
 * 26 weeks × 7 days
 */
@Composable
fun ContributionHeatmap(
    levels: List<List<Int>>, // 52 weeks × 7 days
    modifier: Modifier = Modifier,
) {

    val cellSize = 14.dp
    val cellSpacing = 3.dp

    val cellColors = listOf(
        Color(0xFF161B22), // empty
        Color(0xFF0E4429),
        Color(0xFF006D32),
        Color(0xFF26A641),
        Color(0xFF39D353),
    )

    val scrollState = rememberScrollState()

    //  Month positions (approx GitHub style)
    val months = List(52) { index ->
        when (index) {
            0 -> "Jan"
            4 -> "Feb"
            8 -> "Mar"
            13 -> "Apr"
            17 -> "May"
            21 -> "Jun"
            26 -> "Jul"
            30 -> "Aug"
            35 -> "Sep"
            39 -> "Oct"
            43 -> "Nov"
            47 -> "Dec"
            else -> ""
        }
    }

    Column(modifier = modifier) {

        //  MONTH LABELS (SCROLL WITH GRID)
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(start = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(cellSpacing)
        ) {
            months.forEach {
                Box(
                    modifier = Modifier.width(23.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (it.isNotEmpty()) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(9.dp))

        Row {

            // DAY LABELS (FIXED)
            Column(
                modifier = Modifier.width(18.dp),
                verticalArrangement = Arrangement.spacedBy(cellSpacing)
            ) {
                val days = listOf("M", "T", "W", "T", "F", "S", "S")

                days.forEachIndexed { index, day ->
                    Box(
                        modifier = Modifier.height(cellSize),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        // show alternate labels (like GitHub)
                        if (index % 2 == 0) {
                            Text(
                                text = day,
                                style = MaterialTheme.typography.displayMedium.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            )
                        }
                    }
                }
            }

            //  HEATMAP GRID (SCROLLABLE)
            Column(
                modifier = Modifier.horizontalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(cellSpacing)
            ) {
                repeat(7) { dayIndex ->
                    Row(horizontalArrangement = Arrangement.spacedBy(cellSpacing)) {
                        repeat(levels.size) { weekIndex ->
                            val level =
                                levels.getOrNull(weekIndex)?.getOrNull(dayIndex) ?: 0

                            Box(
                                modifier = Modifier
                                    .size(cellSize)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(
                                        cellColors[level.coerceIn(0, 4)]
                                    )
                            )
                        }
                    }
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



