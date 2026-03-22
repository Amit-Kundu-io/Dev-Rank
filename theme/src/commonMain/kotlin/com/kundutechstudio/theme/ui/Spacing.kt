package com.kundutechstudio.theme.ui

import androidx.compose.ui.unit.dp


object Spacing {
    val xs = 4.dp   // icon gaps, tiny inner separators
    val sm = 8.dp   // chip row gaps, inline element spacing
    val md = 12.dp   // card inner padding (compact mode)
    val lg = 16.dp   // standard card padding, section vertical gap
    val xl = 20.dp   // screen horizontal padding (left/right)
    val xxl = 24.dp   // hero section padding, podium padding
    val xxxl = 32.dp   // between major screen sections
    val huge = 48.dp   // splash / empty state breathing room
}

// ── Avatar Sizes ──────────────────────────────────────────────────
object AvatarSize {
    val xs = 26.dp   // inline mention chips
    val sm = 32.dp   // leaderboard list row
    val md = 40.dp   // horizontal scroll dev card
    val lg = 52.dp   // "Your Rank" bottom card
    val xl = 68.dp   // profile hero
    val xxl = 80.dp   // splash / onboarding avatar
}

// ── Icon Sizes ────────────────────────────────────────────────────
object IconSize {
    val xs = 14.dp   // inline language dot indicator
    val sm = 16.dp   // small action icons, chip icons
    val md = 20.dp   // nav bar icons, card action icons
    val lg = 24.dp   // top bar icons, fab icons
    val xl = 32.dp   // feature/section icons
}

// ── Card Dimensions ───────────────────────────────────────────────
object CardSize {
    val repoCardMinWidth = 200.dp   // horizontal scroll repo card
    val repoCardMinHeight = 180.dp   // horizontal scroll repo card
    val devCardMinWidth = 120.dp   // horizontal scroll dev card
    val rankBadgeWidth = 28.dp    // rank number badge width
    val rankBadgeHeight = 28.dp    // rank number badge height
    val statBandHeight = 56.dp    // profile stats band height
    val bottomCardHeight = 120.dp   // "Your Rank" sticky bottom
    val podiumBase1Height = 64.dp    // rank #1 podium platform
    val podiumBase2Height = 48.dp    // rank #2 podium platform
    val podiumBase3Height = 36.dp    // rank #3 podium platform
    val navBarHeight = 72.dp    // bottom navigation bar
    val chipHeight = 30.dp    // filter chip height
    val heatmapCellSize = 10.dp    // contribution heatmap cell
    val langBarHeight = 8.dp     // language breakdown bar
    val miniBarsHeight = 44.dp    // commit activity mini bars
}

// ── Border Widths ─────────────────────────────────────────────────
object BorderWidth {
    val thin = 0.5.dp   // subtle card borders
    val default = 1.dp     // standard card outline
    val medium = 1.5.dp   // phone shell border
    val thick = 2.dp     // active/selected state border
    val accent = 2.5.dp   // gold border on rank #1 avatar
}

// ── Elevation ─────────────────────────────────────────────────────
// DevRank is flat-first — use color layering not real shadows
object Elevation {
    val none = 0.dp
    val card = 0.dp   // cards rely on border + bg color
    val modal = 0.dp   // sheets rely on BgOverlay color
    val raised = 2.dp   // rare: floating action button only
}
