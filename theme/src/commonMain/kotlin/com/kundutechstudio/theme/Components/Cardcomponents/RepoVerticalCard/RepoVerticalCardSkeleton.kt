package com.kundutechstudio.theme.Components.Cardcomponents.RepoVerticalCard

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing

@Composable
fun RepoVerticalCardSkeleton(
    modifier: Modifier = Modifier,
) {
    val shimmer = shimmerBrush()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(135.dp)
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .padding(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
    ) {
        // ── Row 1: repo name + trending badge ─────────────────────
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            SkelBox(
                modifier = Modifier.fillMaxWidth(0.6f).height(14.dp),
                brush = shimmer,
            )
            Spacer(Modifier.width(Spacing.sm))
            SkelBox(
                modifier = Modifier.width(48.dp).height(18.dp),
                brush = shimmer,
                shape = PillShape,
            )
        }

        // ── Description line 1 ────────────────────────────────────
        SkelBox(
            modifier = Modifier.fillMaxWidth().height(11.dp),
            brush = shimmer,
        )

        // ── Description line 2 (shorter) ─────────────────────────
        SkelBox(
            modifier = Modifier.fillMaxWidth(0.78f).height(11.dp),
            brush = shimmer,
        )

        // ── Row 3: star chip + lang dot + lang name ───────────────
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        ) {
            // Star chip
            SkelBox(
                modifier = Modifier.width(52.dp).height(20.dp),
                brush = shimmer,
                shape = PillShape,
            )
            // Lang dot
            SkelBox(
                modifier = Modifier.size(8.dp),
                brush = shimmer,
                shape = AvatarShape,
            )
            // Lang name
            SkelBox(
                modifier = Modifier.width(56.dp).height(10.dp),
                brush = shimmer,
            )
        }
    }
}

// ── Shimmer brush (shared across all skeleton boxes) ──────────────
@Composable
private fun shimmerBrush(): Brush {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateX by transition.animateFloat(
        initialValue = -700f,
        targetValue = 700f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1100, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmerX",
    )
    return Brush.linearGradient(
        colors = listOf(
            BgSubtle,
            Color(0xFF2D3340),
            BgInset.copy(alpha = 0.95f),
            Color(0xFF2D3340),
            BgSubtle,
        ),
        start = Offset(translateX, 0f),
        end = Offset(translateX + 500f, 200f),
    )
}

// ── Single skeleton box ───────────────────────────────────────────
@Composable
private fun SkelBox(
    modifier: Modifier,
    brush: Brush,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(6.dp),
) {
    Box(modifier = modifier.clip(shape).background(brush))
}