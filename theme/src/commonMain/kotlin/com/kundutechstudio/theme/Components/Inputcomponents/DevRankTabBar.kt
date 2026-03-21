package com.kundutechstudio.theme.Components.Inputcomponents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextSubtle

/**
 * TabBar — underline-style tab bar
 * Used on Bookmarks screen: "Repositories (7)" / "Developers (4)"
 */
@Composable
fun DevRankTabBar(
    tabs: List<String>,
    selectedIndex: Int = 0,
    onSelect: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
    ) {
        tabs.forEachIndexed { index, label ->
            val isSelected  = index == selectedIndex
            val textColor   = if (isSelected) AccentBlueLight else TextSubtle
            val borderColor = if (isSelected) AccentBlue else androidx.compose.ui.graphics.Color.Transparent

            Box(
                modifier = Modifier
                    .clickable { onSelect(index) }
                    .border(
                        width  = BorderWidth.thick,
                        color  = borderColor,
                        shape  = androidx.compose.foundation.shape.RoundedCornerShape(
                            topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp,
                        )
                    )
                    .padding(horizontal = Spacing.lg, vertical = Spacing.md),
            ) {
                Text(
                    text       = label,
                    color      = textColor,
                    fontSize   = 12.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewTabBar() {
    DevRankTheme {
        var selected by remember { mutableStateOf(0) }
        Box(modifier = Modifier
            .padding(Spacing.lg)
            .border(BorderWidth.thin, BorderMuted,
                androidx.compose.foundation.shape.RoundedCornerShape(0.dp))) {
            DevRankTabBar(
                tabs          = listOf("📦 Repositories (7)", "👤 Developers (4)"),
                selectedIndex = selected,
                onSelect      = { selected = it },
            )
        }
    }
}

