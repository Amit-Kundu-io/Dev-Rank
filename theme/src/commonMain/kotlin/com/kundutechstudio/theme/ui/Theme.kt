package com.kundutechstudio.theme.ui


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi


private val DevRankDarkColorScheme = darkColorScheme(

    // ── Primary (Blue accent) ──────────────────────────────────
    primary = AccentBlue,
    onPrimary = TextPrimary,
    primaryContainer = AccentBlueGhost,
    onPrimaryContainer = AccentBlueLight,

    // ── Secondary (Green — success / activity) ─────────────────
    secondary = AccentGreen,
    onSecondary = BgDefault,
    secondaryContainer = AccentGreenGhost,
    onSecondaryContainer = AccentGreen,

    // ── Tertiary (Orange — hot / trending) ─────────────────────
    tertiary = AccentOrange,
    onTertiary = BgDefault,
    tertiaryContainer = AccentOrangeGhost,
    onTertiaryContainer = AccentOrange,

    // ── Error (Red — danger / trend down) ──────────────────────
    error = AccentRed,
    onError = TextPrimary,
    errorContainer = AccentRedGhost,
    onErrorContainer = AccentRed,

    // ── Backgrounds ────────────────────────────────────────────
    background = BgDefault,
    onBackground = TextPrimary,

    // ── Surface (cards, sheets, nav bar) ───────────────────────
    surface = BgOverlay,
    onSurface = TextSecondary,
    surfaceVariant = BgInset,
    onSurfaceVariant = TextMuted,

    // ── Inverse ────────────────────────────────────────────────
    inverseSurface = TextPrimary,
    inverseOnSurface = BgDefault,
    inversePrimary = AccentBlueDark,

    // ── Outline ────────────────────────────────────────────────
    outline = BorderDefault,
    outlineVariant = BorderMuted,

    // ── Scrim (modal backdrop) ─────────────────────────────────
    scrim = BgCanvas,
)


@Composable
fun DevRankTheme(
    content: @Composable () -> Unit
) {
    SetDarkStatusBar()
    MaterialTheme(
        colorScheme = DevRankDarkColorScheme,
        typography = AppTypography(),
        shapes = DevRankShapes,
        content = content,
    )
}