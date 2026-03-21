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
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPlaceholder
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle

/**
 * YourRankCard — sticky bottom card always visible on Leaderboard screen
 */
@Composable
fun YourRankCard(
    initials: String,
    username: String,
    handle: String,
    globalRank: Int,
    followers: String,
    repos: String,
    stars: String,
    followersDelta: String,
    reposDelta: String,
    starsDelta: String,
    avatarColor: Color = AccentBlue,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BgOverlay)
            .border(
                BorderWidth.default, BorderMuted,
                androidx.compose.foundation.shape.RoundedCornerShape(0.dp)
            )
            .padding(horizontal = Spacing.xl, vertical = Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.md),
    ) {
        // Header label
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(AvatarShape)
                    .background(AccentBlue)
            )
            Text(
                text = "YOUR RANK",
                color = TextPlaceholder,
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.12.sp,
            )
        }

        // Avatar + name + rank pill
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        ) {
            DevRankAvatar(
                initials = initials,
                size = AvatarSize.lg,
                color = avatarColor,
                borderColor = AccentBlue.copy(0.4f),
                borderWidth = BorderWidth.thick,
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = username,
                    color = TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
                Text(
                    text = "@$handle",
                    color = TextMuted,
                    fontSize = 11.sp,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                )
            }

            // Rank pill
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(DevRankShapes.medium)
                    .background(AccentBlueGhost)
                    .border(BorderWidth.default, AccentBlue.copy(0.45f), DevRankShapes.medium)
                    .padding(horizontal = Spacing.lg, vertical = Spacing.md),
            ) {
                Text(
                    text = "Global",
                    color = TextMuted,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.08.sp,
                )
                Text(
                    text = "#$globalRank",
                    color = AccentBlue,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                    lineHeight = 24.sp,
                )
                Text(text = "Top 0.05%", color = AccentBlueLight.copy(0.7f), fontSize = 9.sp)
            }
        }

        // 3-column stat band
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(DevRankShapes.medium)
                .background(BgInset)
                .border(BorderWidth.default, BorderMuted, DevRankShapes.medium),
        ) {
            listOf(
                Triple(followers, "Followers", followersDelta),
                Triple(repos, "Repos", reposDelta),
                Triple(stars, "Stars", starsDelta),
            ).forEachIndexed { i, (value, label, delta) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = Spacing.md)
                        .then(
                            if (i > 0) Modifier.border(
                                BorderWidth.thin, BorderMuted,
                                androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
                            ) else Modifier
                        ),
                ) {
                    Text(
                        text = value,
                        color = TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                    )
                    Text(
                        text = label,
                        color = TextSubtle,
                        fontSize = 9.sp,
                        letterSpacing = 0.05.sp,
                    )
                    Text(
                        text = "▲ +$delta",
                        color = AccentGreen,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewYourRankCard() {
    DevRankTheme {
        YourRankCard(
            initials = "AK",
            username = "Arjun Kapoor",
            handle = "arjun-kapoor",
            globalRank = 2456,
            followers = "3.2K",
            repos = "128",
            stars = "24K",
            followersDelta = "22",
            reposDelta = "3",
            starsDelta = "140",
        )
    }
}

