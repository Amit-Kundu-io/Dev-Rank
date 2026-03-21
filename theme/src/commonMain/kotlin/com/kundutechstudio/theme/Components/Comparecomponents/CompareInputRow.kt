package com.kundutechstudio.theme.Components.Comparecomponents


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderDefault
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPlaceholder



/**
 * CompareInputRow — two username inputs + VS badge
 */
@Composable
fun CompareInputRow(
    usernameA: String = "",
    usernameB: String = "",
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.xl),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .clip(DevRankShapes.small)
                .background(BgOverlay)
                .border(BorderWidth.default, BorderMuted, DevRankShapes.small)
                .padding(horizontal = Spacing.md, vertical = Spacing.sm),
        ) {
            Text(
                text = usernameA.ifEmpty { "Username A" },
                color = if (usernameA.isEmpty()) TextPlaceholder else AccentBlueLight,
                fontSize = 12.sp,
            )
        }
        VsBadge()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .clip(DevRankShapes.small)
                .background(BgOverlay)
                .border(BorderWidth.default, BorderMuted, DevRankShapes.small)
                .padding(horizontal = Spacing.md, vertical = Spacing.sm),
        ) {
            Text(
                text = usernameB.ifEmpty { "Username B" },
                color = if (usernameB.isEmpty()) TextPlaceholder else TextMuted,
                fontSize = 12.sp,
            )
        }
    }
}

/**
 * VsBadge — centre "VS" circle divider
 */
@Composable
fun VsBadge(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(36.dp)
            .clip(AvatarShape)
            .background(BgInset)
            .border(BorderWidth.thick, BorderDefault, AvatarShape),
    ) {
        Text(
            text = "VS",
            color = TextMuted,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewCompareInputRow() {
    DevRankTheme {
        CompareInputRow(
            usernameA = "torvalds",
            modifier = Modifier.padding(Spacing.lg),
        )
    }
}