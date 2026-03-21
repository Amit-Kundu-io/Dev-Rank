package com.kundutechstudio.theme.Components.Inputcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted


/**
 * FilterChipRow — horizontal scrollable filter chips
 * e.g. "Today", "Weekly", "All Time"  or  "Users", "Repos", "Orgs"
 */
@Composable
fun FilterChipRow(
    chips: List<String>,
    selectedIndex: Int = 0,
    onSelect: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        modifier              = modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = Spacing.xl),
    ) {
        chips.forEachIndexed { index, label ->
            FilterChip(
                label      = label,
                isSelected = index == selectedIndex,
                onClick    = { onSelect(index) },
            )
        }
    }
}

/**
 * FilterChip — single selectable chip
 */
@Composable
fun FilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val bgColor     = if (isSelected) AccentBlueGhost else BgOverlay
    val borderColor = if (isSelected) AccentBlue.copy(0.4f) else BorderMuted
    val textColor   = if (isSelected) AccentBlueLight else TextMuted

    Box(
        contentAlignment = Alignment.Center,
        modifier         = modifier
            .height(CardSize.chipHeight)
            .clip(PillShape)
            .background(bgColor)
            .border(BorderWidth.default, borderColor, PillShape)
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.md),
    ) {
        Text(
            text       = label,
            color      = textColor,
            fontSize   = 11.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}



@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewFilterChips() {
    DevRankTheme {
        var selected by remember { mutableStateOf(1) }
        Column(verticalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(vertical = Spacing.lg)) {
            FilterChipRow(
                chips         = listOf("Today", "Weekly", "All Time"),
                selectedIndex = selected,
                onSelect      = { selected = it },
            )
            FilterChipRow(
                chips         = listOf("👤 Users", "📦 Repos", "🏢 Orgs", "🏷 Topics"),
                selectedIndex = 0,
            )
        }
    }
}

