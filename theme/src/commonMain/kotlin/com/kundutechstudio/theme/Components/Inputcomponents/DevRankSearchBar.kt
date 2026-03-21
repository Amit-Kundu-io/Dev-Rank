package com.kundutechstudio.theme.Components.Inputcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderDefault
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPlaceholder
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle

/**
 * DevRankSearchBar — main search input
 *
 * @param query         current text (empty string shows placeholder)
 * @param placeholder   placeholder text
 * @param isActive      true = blue border (focused state)
 */
@Composable
fun DevRankSearchBar(
    query: String = "",
    placeholder: String = "Search developers & repos...",
    isActive: Boolean = false,
    onClear: () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val borderColor = if (isActive) AccentBlue else BorderDefault

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        modifier = modifier
            .fillMaxWidth()
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, borderColor, DevRankShapes.medium)
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.lg, vertical = Spacing.md),
    ) {
        Text("🔍", fontSize = 14.sp, color = TextSubtle.copy(alpha = 0f)) // icon placeholder
        Text(
            text = if (query.isEmpty()) placeholder else query,
            color = if (query.isEmpty()) TextPlaceholder else TextPrimary,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f),
        )
        if (query.isNotEmpty()) {
            Text(
                text = "✕",
                color = TextSubtle,
                fontSize = 13.sp,
                modifier = Modifier.clickable(onClick = onClear),
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(DevRankShapes.extraSmall)
                    .background(BgSubtle)
                    .border(BorderWidth.thin, BorderMuted, DevRankShapes.extraSmall)
                    .padding(horizontal = 5.dp, vertical = 2.dp),
            ) {
//                Text(
//                    text = "⌘K", color = TextPlaceholder, fontSize = 10.sp,
//                    fontFamily = androidx.compose.material3.MaterialTheme.typography.labelMedium.fontFamily
//                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewSearchBarEmpty() {
    DevRankTheme {
        DevRankSearchBar(modifier = Modifier.padding(Spacing.lg))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewSearchBarActive() {
    DevRankTheme {
        DevRankSearchBar(
            query = "torval",
            isActive = true,
            modifier = Modifier.padding(Spacing.lg),
        )
    }
}


