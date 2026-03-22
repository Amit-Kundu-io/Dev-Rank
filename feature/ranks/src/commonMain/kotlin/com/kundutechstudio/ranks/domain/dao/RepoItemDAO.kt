package com.kundutechstudio.ranks.domain.dao

import androidx.compose.ui.graphics.Color
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingType

data class RepoItemDAO(
    val id: Long,
    val name: String,
    val owner: String,
    val description: String,
    val stars: String,
    val language: String,
    val langColor: Color,
    val rank: Int? = null,
    val trendingLabel: String? = null,
    val trendingType: TrendingType = TrendingType.HOT,
)
