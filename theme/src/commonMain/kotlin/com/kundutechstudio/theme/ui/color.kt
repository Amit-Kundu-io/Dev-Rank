package com.kundutechstudio.theme.ui

import androidx.compose.ui.graphics.Color


// ── Backgrounds (Elevation Scale) ────────────────────────────────
val BgCanvas = Color(0xFF010409)   // deepest bg, status bar area
val BgDefault = Color(0xFF0D1117)   // primary screen background
val BgOverlay = Color(0xFF161B22)   // cards, bottom sheets, nav bar
val BgInset = Color(0xFF1C2128)   // inputs, nested cards, stat bands
val BgSubtle = Color(0xFF21262D)   // hover rows, pressed states, chips

// ── Borders ───────────────────────────────────────────────────────
val BorderDefault = Color(0xFF30363D)   // standard card outline
val BorderMuted = Color(0xFF21262D)   // subtle row dividers
val BorderStrong = Color(0xFF484F58)   // emphasis / active borders

// ── Text Scale ────────────────────────────────────────────────────
val TextPrimary = Color(0xFFF0F6FC)   // page titles, screen headings
val TextSecondary = Color(0xFFE6EDF3)   // body text, stat values
val TextTertiary = Color(0xFFC9D1D9)   // supporting text, descriptions
val TextMuted = Color(0xFF8B949E)   // subtitles, metadata, labels
val TextSubtle = Color(0xFF6E7681)   // hints, timestamps, secondary meta
val TextPlaceholder = Color(0xFF484F58)   // search bar placeholder text
val TextDisabled = Color(0xFF30363D)   // disabled elements, inactive nav

// ── Primary Accent — Blue ─────────────────────────────────────────
val AccentBlue = Color(0xFF2563EB)   // CTAs, active chips, links
val AccentBlueLight = Color(0xFF3B82F6)   // hover, score values, rank badges
val AccentBlueDark = Color(0xFF1D4ED8)   // pressed state, avatar gradients
val AccentBlueGhost = Color(0x1F2563EB)   // chip fills, badge backgrounds

// ── Secondary Accent — Green (success / trending up) ─────────────
val AccentGreen = Color(0xFF22C55E)   // trend up arrows, active dot, heatmap
val AccentGreenLight = Color(0xFF4ADE80)   // lighter green highlight
val AccentGreenGhost = Color(0x1F22C55E)   // winner card tint, success bg

// ── Danger Accent — Red (error / trending down) ──────────────────
val AccentRed = Color(0xFFEF4444)   // trend down arrows, error states
val AccentRedGhost = Color(0x1FEF4444)   // error bg, danger tint

// ── Warning Accent — Orange (hot badge / warning) ────────────────
val AccentOrange = Color(0xFFF97316)   // "Hot" trending badge
val AccentOrangeGhost = Color(0x1FF97316)   // warning tint background

// ── Rank Medal Colors ─────────────────────────────────────────────
val RankGold = Color(0xFFFFD700)   // rank #1 text, border, score
val RankGoldGhost = Color(0x1FFFD700)   // rank #1 row background tint
val RankSilver = Color(0xFFC0C0C0)   // rank #2 text, border, score
val RankSilverGhost = Color(0x1FC0C0C0)   // rank #2 row background tint
val RankBronze = Color(0xFFCD7F32)   // rank #3 text, border, score
val RankBronzeGhost = Color(0x1FCD7F32)   // rank #3 row background tint

// ── Star / Repository Highlight ───────────────────────────────────
val StarYellow = Color(0xFFF2CC60)   // star count numbers
val StarYellowGhost = Color(0x1FF2CC60)   // star chip background

// ── Language Dot Colors (GitHub standard) ────────────────────────
val LangKotlin = Color(0xFFF18E33)
val LangJava = Color(0xFFB07219)
val LangPython = Color(0xFF3572A5)
val LangJavaScript = Color(0xFFF1E05A)
val LangTypeScript = Color(0xFF3178C6)
val LangC = Color(0xFF555555)
val LangCPP = Color(0xFFF34B7D)
val LangRust = Color(0xFFDEA584)
val LangSwift = Color(0xFFF05138)
val LangDart = Color(0xFF00B4AB)
val LangGo = Color(0xFF00ADD8)
val LangRuby = Color(0xFF701516)
val LangShell = Color(0xFF89E051)
val LangHtml = Color(0xFFE34C26)
val LangCss = Color(0xFF563D7C)