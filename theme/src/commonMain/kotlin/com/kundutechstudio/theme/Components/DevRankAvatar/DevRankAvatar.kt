package com.kundutechstudio.theme.Components.DevRankAvatar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueDark
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AccentOrange
import com.kundutechstudio.theme.ui.AccentRed
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.RankBronze
import com.kundutechstudio.theme.ui.RankGold
import com.kundutechstudio.theme.ui.RankSilver
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPrimary

// DEVRANK — Avatar Components

/**
 * DevRankAvatar — initials-based circle avatar
 *
 * @param initials  2-letter initials e.g. "TL", "AK"
 * @param size      use AvatarSize.sm / md / lg / xl
 * @param color     background gradient start color
 * @param borderColor optional medal border (RankGold, RankSilver, etc.)
 * @param borderWidth use BorderWidth tokens
 */
@Composable
fun DevRankAvatar(
    initials: String,
    size: Dp = AvatarSize.md,
    color: Color = AccentBlue,
    borderColor: Color? = null,
    borderWidth: Dp = BorderWidth.default,
    modifier: Modifier = Modifier,
) {
    val fontSize = when {
        size <= AvatarSize.xs -> 10.sp
        size <= AvatarSize.sm -> 12.sp
        size <= AvatarSize.md -> 14.sp
        size <= AvatarSize.lg -> 18.sp
        size <= AvatarSize.xl -> 22.sp
        else -> 26.sp
    }

    val base = Modifier
        .size(size)
        .clip(AvatarShape)
        .background(color)
        .then(
            if (borderColor != null)
                Modifier.border(borderWidth, borderColor, AvatarShape)
            else Modifier
        )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.then(base),
    ) {
        Text(
            text = initials.take(2).uppercase(),
            color = TextPrimary,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
        )
    }
}



// Previews

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewAvatarSizes() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.md),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(Spacing.lg),
        ) {
            DevRankAvatar("TL", AvatarSize.xs, AccentBlue)
            DevRankAvatar("TL", AvatarSize.sm, AccentGreen)
            DevRankAvatar("TL", AvatarSize.md, AccentRed)
            DevRankAvatar("TL", AvatarSize.lg, AccentOrange)
            DevRankAvatar("TL", AvatarSize.xl, AccentBlueDark)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewAvatarMedals() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.md),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(Spacing.lg),
        ) {
            DevRankAvatar(
                "TL", AvatarSize.lg, AccentBlueDark,
                borderColor = RankGold, borderWidth = BorderWidth.accent
            )
            DevRankAvatar(
                "DS", AvatarSize.lg, BgSubtle,
                borderColor = RankSilver, borderWidth = BorderWidth.thick
            )
            DevRankAvatar(
                "MR", AvatarSize.lg, BgInset,
                borderColor = RankBronze, borderWidth = BorderWidth.thick
            )
            OnlineAvatar("AK", AvatarSize.lg, AccentBlue)
        }
    }
}