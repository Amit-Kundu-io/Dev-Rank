package com.kundutechstudio.theme.Components.Statcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing

/**
 * ActivityBars — mini commit activity bar chart
 * Used on Repo Detail screen
 */
@Composable
fun ActivityBars(
    values: List<Float>,   // 0f–1f relative heights
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(CardSize.miniBarsHeight),
    ) {
        values.forEach { v ->
            val barColor = when {
                v >= 0.8f -> AccentBlue
                v >= 0.4f -> AccentBlueGhost.copy(alpha = 0.6f)
                else -> BgSubtle
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(v.coerceIn(0.05f, 1f))
                    .clip(
                        androidx.compose.foundation.shape.RoundedCornerShape(
                            topStart = 2.dp,
                            topEnd = 2.dp
                        )
                    )
                    .background(barColor)
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewActivityBars() {
    DevRankTheme {
        ActivityBars(
            values = listOf(
                0.3f, 0.5f, 0.45f, 0.9f, 0.6f, 0.85f, 0.7f,
                0.4f, 0.65f, 0.2f, 0.75f, 0.5f
            ),
            modifier = Modifier.padding(Spacing.lg),
        )
    }
}




