package com.kundutechstudio.ranks.domain.dao

import androidx.compose.ui.graphics.Color

data class LeaderboardDevItem(
    val rank: Int,
    val initials: String,
    val username: String,
    val subtitle: String,
    val statValue: String,
    val statLabel: String,
    val delta: String,
    val isDeltaUp: Boolean,
    val avatarColor: Color,
    val isMe: Boolean = false,
)