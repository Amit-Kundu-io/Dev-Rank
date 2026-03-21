package com.kundutechstudio.theme.ui


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp


val DevRankShapes = Shapes(
    // Chips, tags, language badges, small buttons
    extraSmall = RoundedCornerShape(6.dp),

    // Input fields, search bars, small stat cards
    small = RoundedCornerShape(8.dp),

    // Standard cards: repo cards, leaderboard rows
    medium = RoundedCornerShape(12.dp),

    // Profile hero card, compare panels, "Your Rank" card
    large = RoundedCornerShape(16.dp),

    // Bottom sheets, dialogs, drawer surfaces
    extraLarge = RoundedCornerShape(24.dp),
)

// ── Custom named shapes for DevRank components ────────────────────

// Rank badge pills: "#1", "#2456", "Top 0.05%"
val PillShape = RoundedCornerShape(99.dp)

// Avatar circles: developer profile images
val AvatarShape = CircleShape

// Podium base for top-3 leaderboard
val PodiumBaseShape = RoundedCornerShape(
    topStart = 10.dp,
    topEnd = 10.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp,
)

// Notch on Android device top bar (visual mock)
val NotchShape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomStart = 16.dp,
    bottomEnd = 16.dp,
)

// Stat band item (flat left/right edge for joined groups)
val StatBandFirstShape = RoundedCornerShape(
    topStart = 12.dp,
    bottomStart = 12.dp,
    topEnd = 0.dp,
    bottomEnd = 0.dp,
)
val StatBandLastShape = RoundedCornerShape(
    topStart = 0.dp,
    bottomStart = 0.dp,
    topEnd = 12.dp,
    bottomEnd = 12.dp,
)
