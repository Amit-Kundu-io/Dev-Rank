package com.kundutechstudio.theme.Components.Profilecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.ui.AccentBlue
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
    followers: String,
    repos: String,
    stars: String,
    avatarColor: Color = AccentBlue,
    modifier: Modifier = Modifier,
    imageUrl: String?,
    bio: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BgOverlay)
            .border(
                BorderWidth.default, BorderMuted,
                RoundedCornerShape(0.dp)
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
                text = "DEV RANK",
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
                    style = MaterialTheme.typography.displayMedium.copy(
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    )
                )
                Text(
                    text = "@$handle",
                    style = MaterialTheme.typography.displayMedium.copy(
                    color = TextMuted,
                    fontSize = 12.sp,
                    fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                )
                )
            }

        }

        Row {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = bio,
                style = MaterialTheme.typography.displayMedium.copy(
                    color = TextPrimary,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
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
                Pair(followers, "Followers"),
                Pair(repos, "Repos"),
                Pair(stars, "Stars"),
            ).forEachIndexed { i, (value, label) ->
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
                        )
                        .padding(vertical = Spacing.xs),
                ) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.displayMedium.copy(
                        color = TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                    )
                    )

                    Text(
                        text = label,
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = TextSubtle,
                            fontSize = 11.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.05.sp,
                        )
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
            followers = "3.2K",
            repos = "128",
            stars = "24K",
            imageUrl = "Amit Kundu | Android Developer | Kotlin | Java",
            bio = "Amit Kundu | Android Developer | Kotlin | JavaAmit Kundu | Android Developer | Kotlin | Java ",
        )
    }
}

