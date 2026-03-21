package com.kundutechstudio.bottom_navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPlaceholder
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomBottomBar(
    items: List<BottomNavItem>,
    currentDestination: NavDestination?,
    onValueChange: (BottomNavItem) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgOverlay)                          // #161B22 — card surface
            .clickable(
                onClick = {},
                interactionSource = null,
                indication = null,
            )
    ) {

        // ── Top divider — subtle gradient line ───────────────────
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .align(Alignment.TopCenter)
        ) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BorderMuted.copy(alpha = 0f),
                        BorderMuted.copy(alpha = 0.6f),
                    ),
                    startY = 0f,
                    endY = size.height,
                )
            )
        }

        // ── Nav items row ────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = Spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            items.forEach { screen ->

                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true

                // ── Animated colors ──────────────────────────────
                val iconTint by animateColorAsState(
                    targetValue = if (isSelected) AccentBlueLight else TextPlaceholder,
                    animationSpec = tween(200),
                    label = "iconTint",
                )
                val labelColor by animateColorAsState(
                    targetValue = if (isSelected) AccentBlueLight else TextPlaceholder,
                    animationSpec = tween(200),
                    label = "labelColor",
                )

                // ── Animated indicator width (0 → 40dp) ──────────
                val indicatorWidth by animateDpAsState(
                    targetValue = if (isSelected) 40.dp else 0.dp,
                    animationSpec = tween(250),
                    label = "indicatorWidth",
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = Spacing.sm),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    // ── Icon ─────────────────────────────────────
                    Icon(
                        painter = painterResource(
                            screen.selectedIcon
                        ),
                        contentDescription = screen.label,
                        tint = iconTint,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onValueChange(screen) },
                            ),
                    )

                    Spacer(Modifier.height(4.dp))

                    // ── Label ─────────────────────────────────────
                    Text(
                        text = screen.label,
                        color = labelColor,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        letterSpacing = 0.04.sp,
                    )

                    Spacer(Modifier.height(6.dp))

                    // ── Active indicator bar (animated width) ─────
                    Box(
                        modifier = Modifier
                            .width(indicatorWidth)
                            .height(3.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 6.dp,
                                    topEnd = 6.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 0.dp,
                                )
                            )
                            .background(
                                if (isSelected) AccentBlue else Color.Transparent
                            )
                    )
                }
            }
        }
    }
}

