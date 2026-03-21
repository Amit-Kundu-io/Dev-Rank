package com.kundutechstudio.theme.Components.DevRankAvatar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgOverlay

/**
 * OnlineAvatar — avatar with a green online indicator dot
 */
@Composable
fun OnlineAvatar(
    initials: String,
    size: Dp = AvatarSize.md,
    color: Color = AccentBlue,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        DevRankAvatar(initials = initials, size = size, color = color)
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(AvatarShape)
                .background(AccentGreen)
                .border(2.dp, BgOverlay, AvatarShape)
                .align(Alignment.BottomEnd)
        )
    }
}