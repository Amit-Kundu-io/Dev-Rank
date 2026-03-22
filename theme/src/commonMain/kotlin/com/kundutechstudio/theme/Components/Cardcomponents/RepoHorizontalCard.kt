package com.kundutechstudio.theme.Components.Cardcomponents

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.RankBadge
import com.kundutechstudio.theme.Components.Badgecomponents.StarChip
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingBadge
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingType
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.LangJavaScript
import com.kundutechstudio.theme.ui.LangTypeScript
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle


@Composable
fun RepoHorizontalCard(
    repoName: String,
    ownerName: String,
    description: String,
    stars: String,
    language: String,
    langColor: Color,
    rank: Int? = null,
    trendingLabel: String? = null,
    trendingType: TrendingType = TrendingType.HOT,
    isLoading: Boolean = false,               // ← pass true while data is fetching
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    // Real content
    Column(
        modifier = modifier
            .width(CardSize.repoCardMinWidth)
            .height(CardSize.repoCardMinHeight)
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .padding(Spacing.md),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        // ── Row 1: rank badge / trending + star chip ──────────────
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            rank?.let { RankBadge(it) }
            trendingLabel?.let { TrendingBadge(it, trendingType) }
            StarChip(stars)
        }

        // ── Repo name ─────────────────────────────────────────────
        Text(
            text = repoName,
            color = TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        // ── Owner ─────────────────────────────────────────────────
        Text(
            text = "@$ownerName",
            color = TextSubtle,
            fontSize = 10.sp,
            maxLines = 1,
        )

        // ── Description ───────────────────────────────────────────
        Text(
            text = description,
            color = TextMuted,
            fontSize = 10.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 14.sp,
        )

        // ── Language row ──────────────────────────────────────────
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(AvatarShape)
                        .background(langColor)
                )
                Text(text = language, color = TextMuted, fontSize = 10.sp, lineHeight = 11.sp)
            }
        }
    }
}


// ── Shimmer brush ─────────────────────────────────────────────────
// Shared animated shimmer used by all skeleton elements
@Composable
private fun shimmerBrush(): Brush {
    val shimmerColors = listOf(
        BgSubtle,                          // base dark
        BgInset.copy(alpha = 0.9f),        // slightly lighter peak
        Color(0xFF2D3340).copy(alpha = 0.8f), // highlight
        BgInset.copy(alpha = 0.9f),
        BgSubtle,
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateX by transition.animateFloat(
        initialValue = -600f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmerX",
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateX, 0f),
        end = Offset(translateX + 400f, 200f),
    )
}

// ── Skeleton element ──────────────────────────────────────────────
@Composable
private fun SkeletonBox(
    modifier: Modifier,
    shimmer: Brush,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(6.dp),
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(shimmer)
    )
}


@Composable
fun RepoHorizontalCardSkeleton(
    modifier: Modifier = Modifier,
) {
    val shimmer = shimmerBrush()

    Column(
        modifier = modifier
            .width(CardSize.repoCardMinWidth)
            .height(CardSize.repoCardMinHeight)
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .padding(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
    ) {
        // ── Row 1: rank badge + star chip ─────────────────────────
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            SkeletonBox(
                modifier = Modifier
                    .width(CardSize.rankBadgeWidth)
                    .height(CardSize.rankBadgeHeight),
                shimmer = shimmer,
                shape = DevRankShapes.small,
            )
            SkeletonBox(
                modifier = Modifier.width(56.dp).height(20.dp),
                shimmer = shimmer,
                shape = PillShape,
            )
        }

        // ── Repo name ─────────────────────────────────────────────
        SkeletonBox(
            modifier = Modifier.fillMaxWidth(0.75f).height(14.dp),
            shimmer = shimmer,
        )

        // ── Owner ─────────────────────────────────────────────────
        SkeletonBox(
            modifier = Modifier.fillMaxWidth(0.45f).height(10.dp),
            shimmer = shimmer,
        )

        // ── Description lines ─────────────────────────────────────
        SkeletonBox(modifier = Modifier.fillMaxWidth().height(10.dp), shimmer = shimmer)
        SkeletonBox(modifier = Modifier.fillMaxWidth(0.85f).height(10.dp), shimmer = shimmer)

        // ── Language row ──────────────────────────────────────────
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            SkeletonBox(
                modifier = Modifier.size(8.dp),
                shimmer = shimmer,
                shape = AvatarShape,
            )
            SkeletonBox(
                modifier = Modifier.width(48.dp).height(10.dp),
                shimmer = shimmer,
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────
// RepoHorizontalCard — real + skeleton via isLoading param
// ─────────────────────────────────────────────────────────────────


@Preview(showBackground = true, backgroundColor = 0xFF0D1117, name = "Card — Loaded")
@Composable
private fun PreviewLoaded() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            RepoHorizontalCard(
                repoName = "freeCodeCamp",
                ownerName = "freeCodeCamp",
                description = "Open-source codebase & curriculum. Learn to code for free.",
                stars = "383K",
                language = "JavaScript",
                langColor = LangJavaScript,
                rank = 1,
                isLoading = false,
            )
            RepoHorizontalCard(
                repoName = "vercel/next.js",
                ownerName = "vercel",
                description = "The React Framework for the Web.",
                stars = "118K",
                language = "TypeScript",
                langColor = LangTypeScript,
                trendingLabel = "HOT",
                trendingType = TrendingType.HOT,
                isLoading = false,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117, name = "Card — Loading (skeleton)")
@Composable
private fun PreviewLoading() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            RepoHorizontalCard(
                repoName = "",
                ownerName = "",
                description = "",
                stars = "",
                language = "",
                langColor = Color.Transparent,
                isLoading = true,
            )
            RepoHorizontalCard(
                repoName = "",
                ownerName = "",
                description = "",
                stars = "",
                language = "",
                langColor = Color.Transparent,
                isLoading = true,
            )
            RepoHorizontalCard(
                repoName = "",
                ownerName = "",
                description = "",
                stars = "",
                language = "",
                langColor = Color.Transparent,
                isLoading = true,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117, name = "Skeleton only")
@Composable
private fun PreviewSkeletonDirect() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            RepoHorizontalCardSkeleton()
            RepoHorizontalCardSkeleton()
        }
    }
}








// Previews

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewRepoHorizontalCard() {
    DevRankTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            RepoHorizontalCard(
                repoName = "freeCodeCamp",
                ownerName = "freeCodeCamp",
                description = "Open-source codebase and curriculum. Learn to code for free.",
                stars = "383K",
                language = "JavaScript",
                langColor = LangJavaScript,
                rank = 1,
            )
            RepoHorizontalCard(
                repoName = "microsoft/vscode",
                ownerName = "microsoft",
                description = "Visual Studio Code — open source code editor.",
                stars = "162K",
                language = "TypeScript",
                langColor = LangTypeScript,
                trendingLabel = "HOT",
                trendingType = TrendingType.HOT,
            )
        }
    }
}





