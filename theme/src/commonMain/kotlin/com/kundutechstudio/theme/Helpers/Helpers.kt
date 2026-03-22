package com.kundutechstudio.theme.Helpers

import androidx.compose.ui.graphics.Color
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingType

object Helpers {

    fun formatScore(score: Int): String = when {
        score >= 1000 -> "${score / 1000}.${(score % 1000) / 100}K"
        else -> score.toString()
    }

    fun formatNumber(n: Int): String {
        return when {
            n >= 1_000_000 -> "${n / 1_000_000}M"
            n >= 1_000 -> "${n / 1_000}K"
            else -> n.toString()
        }
    }

    fun rankPercentile(rank: Int): String = when {
        rank <= 10 -> "Top 0.001%"
        rank <= 100 -> "Top 0.01%"
        rank <= 1_000 -> "Top 0.1%"
        rank <= 10_000 -> "Top 0.05%"
        else -> "Top 1%"
    }

    fun formatStars(count: Int): String {
        return when {
            count >= 1000 -> "${count / 1000}K"
            else -> count.toString()
        }
    }
    fun getLanguageColor(language: String?): Color {
        return when (language) {
            "JavaScript" -> Color(0xFFF1E05A)
            "TypeScript" -> Color(0xFF3178C6)
            "Python" -> Color(0xFF3572A5)
            "Kotlin" -> Color(0xFFA97BFF)
            else -> Color.Gray
        }
    }
    fun getTrendingType(stars: Int): TrendingType {
        return when {
            stars > 300_000 -> TrendingType.HOT
            stars > 100_000 -> TrendingType.RISING
            stars > 10_000 -> TrendingType.NEW
            else -> TrendingType.BEGINNER
        }
    }



}