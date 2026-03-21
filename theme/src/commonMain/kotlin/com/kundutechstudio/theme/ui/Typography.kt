package com.kundutechstudio.theme.ui


import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import common.resources.Res
import common.resources.jetbrainsmono_bold
import common.resources.jetbrainsmono_medium
import common.resources.jetbrainsmono_regular
import common.resources.jetbrainsmono_semibold
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font


@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppTypography(): Typography {

    val JetBrainsMonoFamily = FontFamily(
        listOf(
            Font(Res.font.jetbrainsmono_regular, FontWeight.Normal),
            Font(Res.font.jetbrainsmono_medium, FontWeight.Medium),
            Font(Res.font.jetbrainsmono_semibold, FontWeight.SemiBold),
            Font(Res.font.jetbrainsmono_bold, FontWeight.Bold),
        )
    )

    return Typography(

        // ── Display ───────────
        displayLarge = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.5).sp,
            color = TextPrimary,
        ),

        // Hero stats
        displayMedium = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            letterSpacing = (-0.3).sp,
            color = TextPrimary,
        ),

        // Large score value
        displaySmall = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            color = TextPrimary,
        ),

        // ── Headlines
        headlineLarge = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.3).sp,
            color = TextPrimary,
        ),

        // Section header
        headlineMedium = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.2).sp,
            color = TextPrimary,
        ),

        // Sub-section ·
        headlineSmall = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            color = TextSecondary,
        ),

        // ── Titles
        titleLarge = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = TextPrimary,
        ),

        // Card sub-headers
        titleMedium = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            color = TextSecondary,
        ),

        // Chip labels
        titleSmall = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.04.sp,
            color = TextMuted,
        ),

        // ── Body
        bodyLarge = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = TextSecondary,
        ),

        // Standard body
        bodyMedium = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = TextMuted,
        ),

        // Small meta
        bodySmall = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = TextSubtle,
        ),

        // ── Labels
        labelLarge = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            color = TextPrimary,
        ),

        // Rank badge
        labelMedium = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            color = TextSecondary,
        ),

        // Tags
        labelSmall = TextStyle(
            fontFamily = JetBrainsMonoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.06.sp,
            color = TextSubtle,
        ),
    )
}