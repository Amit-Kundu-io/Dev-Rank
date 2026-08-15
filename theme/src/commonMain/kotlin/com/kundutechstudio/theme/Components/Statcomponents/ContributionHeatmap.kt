package com.kundutechstudio.theme.Components.Statcomponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing

/**
 * ContributionHeatmap — GitHub-style activity grid
 * 26 weeks × 7 days
 */


private val ContributionColors = listOf(
    Color(0xFF161B22),
    Color(0xFF0E4429),
    Color(0xFF006D32),
    Color(0xFF26A641),
    Color(0xFF39D353),
)

@Composable
fun ContributionHeatmap(
    levels: List<List<Int>>,
    modifier: Modifier = Modifier,
    count: Int = 0,
) {
    val cellSize = 14.dp
    val cellSpacing = 3.dp

    val scrollState = rememberScrollState()

    val months = remember {
        List(52) { index ->
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
    }

    Column(modifier = modifier) {


        Spacer(Modifier.height(6.dp))

        /*
         * MONTHS
         */
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(start = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(cellSpacing)
        ) {
            months.forEach { month ->

                Box(
                    modifier = Modifier.width(cellSize + cellSpacing + 6.dp)
                ) {
                    if (month.isNotEmpty()) {
                        Text(
                            text = month,
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Row {

            /*
             * DAYS
             */
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
                        if (index % 2 == 0) {
                            Text(
                                text = day,
                                fontSize = 11.sp,
                                lineHeight = 11.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            /*
             * HEATMAP
             */
            Row(
                modifier = Modifier.horizontalScroll(scrollState)
            ) {

                Canvas(
                    modifier = Modifier
                        .width((levels.size * (cellSize + cellSpacing)))
                        .height((7 * (cellSize + cellSpacing)))
                ) {

                    val cellWidth = cellSize.toPx()
                    val spacing = cellSpacing.toPx()

                    levels.forEachIndexed { weekIndex, week ->

                        repeat(7) { dayIndex ->

                            val level = week.getOrNull(dayIndex)?.coerceIn(0, 4) ?: 0

                            drawRoundRect(
                                color = ContributionColors[level],
                                topLeft = Offset(
                                    x = weekIndex * (cellWidth + spacing),
                                    y = dayIndex * (cellWidth + spacing)
                                ),
                                size = Size(width = cellWidth, height = cellWidth),
                                cornerRadius = CornerRadius(x = 2.dp.toPx(), y = 2.dp.toPx())
                            )
                        }
                    }
                }
            }
        }



        Spacer(Modifier.height(5.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End

        ){
            Spacer(Modifier.width(15.dp))

            Text(
                text = "$count contributions in this year",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    color = Color.White
                )
            )

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



