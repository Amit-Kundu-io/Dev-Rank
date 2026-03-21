package com.kundutechstudio.theme.Components.Leaderboardcomponents

import androidx.compose.ui.graphics.Color

data class PodiumEntry(
    val initials: String,
    val username: String,
    val score: String,
    val avatarColor: Color,
)