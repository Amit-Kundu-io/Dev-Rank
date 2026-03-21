package com.kundutechstudio.theme


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

}