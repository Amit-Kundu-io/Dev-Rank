package com.kundutechstudio.theme.Components.Profilecomponents


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueDark
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgDefault
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.RankGold
import com.kundutechstudio.theme.ui.RankGoldGhost
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPlaceholder
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle

//Profile & Rank Display Components


/**
 * ProfileHero — top section of User Profile screen
 */
@Composable
fun ProfileHero(
    initials: String,
    displayName: String,
    username: String,
    bio: String,
    location: String = "",
    avatarColor: Color = AccentBlue,
    globalRank: Int,
    devScore: String,
    scoreDelta: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BgDefault)
            .padding(Spacing.xl),
        verticalArrangement = Arrangement.spacedBy(Spacing.md),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.md),
            verticalAlignment = Alignment.Top,
        ) {
            DevRankAvatar(
                initials = initials,
                size = AvatarSize.xl,
                color = avatarColor,
                borderColor = AccentBlue.copy(0.4f),
                borderWidth = BorderWidth.thick,
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = displayName,
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "@$username",
                    color = AccentBlueLight,
                    fontSize = 12.sp,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                )
                if (location.isNotEmpty()) {
                    Text(text = "📍 $location", color = TextSubtle, fontSize = 11.sp)
                }
                Text(
                    text = bio,
                    color = TextMuted,
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                )
            }
        }

        // Rank + Score row
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Rank pill
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clip(DevRankShapes.medium)
                    .background(RankGoldGhost)
                    .border(BorderWidth.default, RankGold.copy(0.4f), DevRankShapes.medium)
                    .padding(vertical = Spacing.md),
            ) {
                Text(
                    text = "🏆 Global Rank",
                    color = RankGold,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.1.sp,
                )
                Text(
                    text = "#$globalRank",
                    color = RankGold,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                    lineHeight = 28.sp,
                )
                Text(text = "Top 0.05%", color = RankGold.copy(0.5f), fontSize = 9.sp)
            }

            // Score pill
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clip(DevRankShapes.medium)
                    .background(BgInset)
                    .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
                    .padding(vertical = Spacing.md),
            ) {
                Text(
                    text = "⬡ Dev Score",
                    color = AccentBlueLight,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.1.sp,
                )
                Text(
                    text = devScore,
                    color = AccentBlueLight,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                    lineHeight = 28.sp,
                )
                Text(text = "▲ +$scoreDelta today", color = AccentGreen, fontSize = 9.sp)
            }
        }
    }
}



// ─────────────────────────────────────────────────────────────────
// Previews
// ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewProfileHero() {
    DevRankTheme {
        ProfileHero(
            initials = "TL",
            displayName = "Linus Torvalds",
            username = "torvalds",
            bio = "Creator of Linux and Git. Work for Linux Foundation.",
            location = "Portland, OR",
            avatarColor = AccentBlueDark,
            globalRank = 1,
            devScore = "9,871",
            scoreDelta = "14",
        )
    }
}

