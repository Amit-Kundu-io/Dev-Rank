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

/**
 * BottomNavBar — 4-item bottom navigation
 */
data class NavItem(
    val icon: String,
    val label: String,
)

@Composable
fun DevRankBottomNav(
    items: List<NavItem>,
    selectedIndex: Int = 0,
    onSelect: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(CardSize.navBarHeight)
            .background(BgOverlay)
            .border(
                width = BorderWidth.default,
                color = BorderMuted,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
            )
            .padding(top = Spacing.md, bottom = Spacing.xxl),
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedIndex
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelect(index) },
            ) {
                Text(
                    text = item.icon,
                    fontSize = 19.sp,
                    color = if (isSelected) AccentBlueLight else TextPlaceholder,
                )
                Text(
                    text = item.label,
                    color = if (isSelected) AccentBlueLight else TextPlaceholder,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.05.sp,
                )
            }
        }
    }
}

/**
 * HomePageHeader — title block at top of Home screen
 */
@Composable
fun HomePageHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.xl, vertical = Spacing.sm),
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = TextPrimary,
            )
            Text(
                text = subtitle,
                color = TextSubtle,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 3.dp),
            )
        }
        NotificationButton(hasNotification = true)
    }
}

// ─────────────────────────────────────────────────────────────────
// Previews
// ─────────────────────────────────────────────────────────────────

val defaultNavItems = listOf(
    NavItem("🏠", "Home"),
    NavItem("🔍", "Search"),
    NavItem("⚡", "Compare"),
    NavItem("🔖", "Saved"),
    NavItem("👤", "Profile"),
)

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

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewBottomNav() {
    DevRankTheme {
        DevRankBottomNav(
            items = defaultNavItems,
            selectedIndex = 0,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewHomeHeader() {
    DevRankTheme {
        HomePageHeader(
            title = "Global Developer\nRankings",
            subtitle = "Updated 3 min ago · 47M developers",
        )
    }
}