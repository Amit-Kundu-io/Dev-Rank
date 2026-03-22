package com.kundutechstudio.ranks.domain.dao

import androidx.compose.ui.graphics.Color


data class DevItem(
    val initials: String,
    val username: String,
    val statValue: String,
    val statLabel: String,
    val avatarColor: Color,
    val rank: Int? = null,
)


