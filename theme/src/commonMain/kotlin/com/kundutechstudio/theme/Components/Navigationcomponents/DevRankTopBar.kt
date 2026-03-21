package com.kundutechstudio.theme.Components.Navigationcomponents


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentRed
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.BgDefault
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.IconSize
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPlaceholder
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle

/**
 * DevRankTopBar — screen top bar with back button, title, action
 */
@Composable
fun DevRankTopBar(
    title: String,
    showBack: Boolean = false,
    onBack: () -> Unit = {},
    trailingContent: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(BgDefault)
            .padding(horizontal = Spacing.xl, vertical = Spacing.md),
    ) {
        if (showBack) {
            Text(
                text = "← Back",
                color = AccentBlueLight,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable(onClick = onBack),
            )
        } else {
            Spacer(Modifier.width(Spacing.xl))
        }

        Text(
            text = title,
            color = TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )

        trailingContent?.invoke() ?: Spacer(Modifier.width(Spacing.xl))
    }
}

/**
 * NotificationButton — bell icon with red dot
 */
@Composable
fun NotificationButton(
    hasNotification: Boolean = true,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(IconSize.xl)
                .clip(DevRankShapes.small)
                .background(BgOverlay)
                .border(BorderWidth.default, BorderMuted, DevRankShapes.small)
                .clickable(onClick = onClick),
        ) {
            Text("🔔", fontSize = 16.sp)
        }
        if (hasNotification) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(AvatarShape)
                    .background(AccentRed)
                    .border(BorderWidth.thick, BgDefault, AvatarShape)
                    .align(Alignment.TopEnd)
                    .offset(x = 2.dp, y = (-2).dp)
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewTopBar() {
    DevRankTheme {
        Column {
            DevRankTopBar(
                title = "Global Developer Rankings",
                showBack = false,
                trailingContent = { NotificationButton() },
            )

            Spacer(modifier = Modifier.height(2.dp).fillMaxWidth().background(Color.White))
            DevRankTopBar(
                title = "Top Developers",
                showBack = true,
                trailingContent = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(IconSize.xl)
                            .clip(DevRankShapes.small)
                            .background(BgOverlay)
                            .border(BorderWidth.default, BorderMuted, DevRankShapes.small),
                    ) { Text("≡", color = TextMuted, fontSize = 16.sp) }
                }
            )
        }
    }
}



