package com.kundutechstudio.ranks.presentation.models


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO
import com.kundutechstudio.theme.Components.Badgecomponents.StarChip
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingBadge
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingType
import com.kundutechstudio.theme.Components.Cardcomponents.DevHorizontalCard
import com.kundutechstudio.theme.Components.Cardcomponents.LeaderboardRow
import com.kundutechstudio.theme.Components.Cardcomponents.RepoHorizontalCard
import com.kundutechstudio.theme.Components.Leaderboardcomponents.DotsSeparator
import com.kundutechstudio.theme.Components.Leaderboardcomponents.SectionHeader
import com.kundutechstudio.theme.Components.Navigationcomponents.DevRankTabs
import com.kundutechstudio.theme.Components.Navigationcomponents.NotificationButton
import com.kundutechstudio.theme.ui.*



data class DevItem(
    val initials: String,
    val username: String,
    val statValue: String,
    val statLabel: String,
    val avatarColor: Color,
    val rank: Int? = null,
)

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

// ── Static sample data ────────────────────────────────────────────

 val topRepos = listOf(
    RepoItemDAO(
        "freeCodeCamp",
        "freeCodeCamp",
        "Open-source codebase & curriculum. Learn to code for free.",
        "383K",
        "JavaScript",
        LangJavaScript,
        rank = 1
    ),
    RepoItemDAO(
        "awesome",
        "sindresorhus",
        "Awesome lists about all kinds of interesting topics.",
        "320K",
        "Markdown",
        Color(0xFF89E051),
        rank = 2
    ),
    RepoItemDAO(
        "coding-interview-university",
        "jwasham",
        "A complete computer science study plan for software engineers.",
        "299K",
        "Markdown",
        Color(0xFF89E051),
        rank = 3
    ),
     RepoItemDAO(
         "996.ICU",
         "996icu",
         "Repo about the 996 working hour system.",
         "269K",
         "HTML",
         LangHtml,
         rank = 4
     ),
)

 val trendingRepos = listOf(
    RepoItemDAO(
        "vercel/next.js",
        "vercel",
        "The React Framework for the Web. Production-grade.",
        "118K",
        "JavaScript",
        LangJavaScript,
        trendingLabel = "HOT",
        trendingType = TrendingType.HOT
    ),
    RepoItemDAO(
        "microsoft/vscode",
        "microsoft",
        "Visual Studio Code — open source code editor by Microsoft.",
        "162K",
        "TypeScript",
        LangTypeScript,
        trendingLabel = "RISING",
        trendingType = TrendingType.RISING
    ),
    RepoItemDAO(
        "torvalds/linux",
        "torvalds",
        "Linux kernel source tree.",
        "182K",
        "C",
        LangC,
        trendingLabel = "HOT",
        trendingType = TrendingType.HOT
    ),
)

 val largestRepos = listOf(
    RepoItemDAO(
        "chromium/chromium",
        "chromium",
        "The official GitHub mirror of the Chromium source.",
        "18K",
        "C++",
        LangCPP,
        trendingLabel = "BIG",
        trendingType = TrendingType.ANDROID
    ),
    RepoItemDAO(
        "android/platform_frameworks_base",
        "android",
        "Base application framework for Android.",
        "14K",
        "Java",
        LangJava,
        trendingLabel = "BIG",
        trendingType = TrendingType.ANDROID
    ),
)

 val topDevelopers = listOf(
    DevItem("TL", "torvalds", "239K", "followers", AccentBlueDark, rank = 1),
    DevItem("DS", "defunkt", "195K", "followers", BgSubtle, rank = 2),
    DevItem("MR", "mojombo", "148K", "followers", Color(0xFF7C2D12), rank = 3),
    DevItem("PH", "pjhyett", "128K", "followers", Color(0xFF6B21A8), rank = 4),
    DevItem("SS", "sindresorhus", "92K", "followers", Color(0xFF1E3A8A), rank = 5),
)

 val topBuilders = listOf(
    DevItem("SS", "sindresorhus", "1.2K", "repos", Color(0xFF1E3A8A), rank = 1),
    DevItem("JH", "joshnh", "848", "repos", Color(0xFF14532D), rank = 2),
    DevItem("AM", "anmolmann", "776", "repos", Color(0xFF7C2D12), rank = 3),
    DevItem("BL", "brandonlehr", "712", "repos", Color(0xFF1E3A8A), rank = 4),
)

 val activeDevs = listOf(
    LeaderboardDevItem(
        1,
        "AK",
        "arjun-dev",
        "14,823 commits · 312 PRs",
        "14.8K",
        "commits",
        "842",
        true,
        AccentBlueDark
    ),
    LeaderboardDevItem(
        2,
        "SC",
        "samuelc",
        "12,210 commits · 288 PRs",
        "12.2K",
        "commits",
        "560",
        true,
        Color(0xFF4338CA)
    ),
    LeaderboardDevItem(
        3,
        "LW",
        "li-wei",
        "11,500 commits · 201 PRs",
        "11.5K",
        "commits",
        "310",
        true,
        Color(0xFF065F46)
    ),
    LeaderboardDevItem(
        12,
        "AK",
        "arjun-kapoor",
        "2,434 commits · 88 PRs",
        "2.4K",
        "commits",
        "22",
        true,
        AccentBlue,
        isMe = true
    ),
)

// ── Tab definition ────────────────────────────────────────────────

 val tabs = listOf(
    "⭐ Repositories",
    "👤 Developers",
    "🔥 Activity",
)

// ─────────────────────────────────────────────────────────────────
// HomeLeaderboardScreen
// ─────────────────────────────────────────────────────────────────

@Composable
fun HomeLeaderboardScreen(
    onRepoClick: (RepoItemDAO) -> Unit = {},
    onDevClick: (DevItem) -> Unit = {},
    onViewAllRepos: () -> Unit = {},
    onViewAllDevs: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
) {
    var selectedTab by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDefault),
        contentPadding = PaddingValues(bottom = CardSize.navBarHeight + Spacing.lg),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {

        // ── Status bar spacer ─────────────────────────────────────
        item { Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars)) }

        // ── Page header ───────────────────────────────────────────
        item {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.xl, vertical = Spacing.md),
            ) {
                Column {
                    Text(
                        text = "Global Developer",
                        style = MaterialTheme.typography.headlineLarge,
                        color = TextPrimary,
                    )
                    Text(
                        text = "Rankings",
                        style = MaterialTheme.typography.headlineLarge,
                        color = TextPrimary,
                    )
                    Text(
                        text = "Updated 3 min ago · 47M developers",
                        color = TextSubtle,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 3.dp),
                    )
                }
                NotificationButton(
                    hasNotification = true,
                    onClick = onNotificationClick,
                )
            }
        }

        // ── Tabs ──────────────────────────────────────────────────
        item {
            DevRankTabs(
                tabs = tabs,
                selected = selectedTab,
                onSelect = { selectedTab = it },
            )
        }

        // ── TAB 0 — Repositories ─────────────────────────────────
        if (selectedTab == 0) {

            // Most Starred
            item {
                SectionHeader(
                    title = "⭐ Most Starred",
                    badgeLabel = "REPOS",
                    badgeColor = StarYellow,
                    badgeBg = StarYellowGhost,
                    onViewAll = onViewAllRepos,
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = Spacing.xl),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                ) {
                    items(topRepos) { repo ->
                        RepoHorizontalCard(
                            repoName = repo.name,
                            ownerName = repo.owner,
                            description = repo.description,
                            stars = repo.stars,
                            language = repo.language,
                            langColor = repo.langColor,
                            rank = repo.rank,
                            onClick = { onRepoClick(repo) },
                        )
                    }
                }
                Spacer(Modifier.height(Spacing.xxl))
            }

            // Trending
            item {
                SectionHeader(
                    title = "🔥 Trending Today",
                    onViewAll = onViewAllRepos,
                )
            }
            items(trendingRepos) { repo ->
                RepoVerticalCard(
                    repo = repo,
                    onClick = { onRepoClick(repo) },
                    modifier = Modifier.padding(horizontal = Spacing.xl),
                )
                Spacer(Modifier.height(Spacing.sm))
            }
            item { Spacer(Modifier.height(Spacing.md)) }

            // Largest Projects
            item {
                SectionHeader(
                    title = "💻 Largest Projects",
                    badgeLabel = "SIZE",
                    badgeColor = AccentBlueLight,
                    badgeBg = AccentBlueGhost,
                    onViewAll = onViewAllRepos,
                )
            }
            items(largestRepos) { repo ->
                RepoVerticalCard(
                    repo = repo,
                    onClick = { onRepoClick(repo) },
                    modifier = Modifier.padding(horizontal = Spacing.xl),
                )
                Spacer(Modifier.height(Spacing.sm))
            }
        }

        // ── TAB 1 — Developers ────────────────────────────────────
        if (selectedTab == 1) {

            // Top Developers
            item {
                SectionHeader(
                    title = "👤 Top Developers",
                    badgeLabel = "LIVE",
                    badgeColor = AccentGreen,
                    badgeBg = AccentGreenGhost,
                    onViewAll = onViewAllDevs,
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = Spacing.xl),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                ) {
                    items(topDevelopers) { dev ->
                        DevHorizontalCard(
                            initials = dev.initials,
                            username = dev.username,
                            statValue = dev.statValue,
                            statLabel = dev.statLabel,
                            avatarColor = dev.avatarColor,
                            rank = dev.rank,
                            onClick = { onDevClick(dev) },
                        )
                    }
                }
                Spacer(Modifier.height(Spacing.xxl))
            }

            // Top Builders
            item {
                SectionHeader(
                    title = "🔨 Top Builders",
                    badgeLabel = "REPOS",
                    badgeColor = AccentBlueLight,
                    badgeBg = AccentBlueGhost,
                    onViewAll = onViewAllDevs,
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = Spacing.xl),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                ) {
                    items(topBuilders) { dev ->
                        DevHorizontalCard(
                            initials = dev.initials,
                            username = dev.username,
                            statValue = dev.statValue,
                            statLabel = dev.statLabel,
                            avatarColor = dev.avatarColor,
                            rank = dev.rank,
                            onClick = { onDevClick(dev) },
                        )
                    }
                }
                Spacer(Modifier.height(Spacing.xxl))
            }
        }

        // ── TAB 2 — Activity ──────────────────────────────────────
        if (selectedTab == 2) {
            item {
                SectionHeader(
                    title = "🔥 Most Active",
                    badgeLabel = "COMMITS",
                    badgeColor = AccentGreen,
                    badgeBg = AccentGreenGhost,
                    onViewAll = {},
                )
            }
            items(activeDevs) { dev ->
                LeaderboardRow(
                    rank = dev.rank,
                    initials = dev.initials,
                    username = dev.username,
                    subtitle = dev.subtitle,
                    statValue = dev.statValue,
                    statLabel = dev.statLabel,
                    delta = dev.delta,
                    isDeltaUp = dev.isDeltaUp,
                    avatarColor = dev.avatarColor,
                    isMe = dev.isMe,
                    modifier = Modifier.padding(horizontal = Spacing.xl),
                )
                Spacer(Modifier.height(Spacing.sm))
            }
            item { DotsSeparator("2,445 more") }
        }
    }
}

// ─────────────────────────────────────────────────────────────────
// DevRankTabs — custom tab bar (no default Material indicator)
// ─────────────────────────────────────────────────────────────────



// ─────────────────────────────────────────────────────────────────
// RepoVerticalCard — inline vertical repo card (no CardComponents dep)
// ─────────────────────────────────────────────────────────────────

@Composable
 fun RepoVerticalCard(
    repo: RepoItemDAO,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .clickable(onClick = onClick)
            .padding(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = repo.name,
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            repo.trendingLabel?.let {
                Spacer(Modifier.width(Spacing.sm))
                TrendingBadge(it, repo.trendingType)
            }
        }

        Text(
            text = repo.description,
            color = TextMuted,
            fontSize = 11.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 16.sp,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        ) {
            StarChip(repo.stars)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(AvatarShape)
                        .background(repo.langColor)
                )
                Text(text = repo.language, color = TextMuted, fontSize = 10.sp)
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────
// Previews
// ─────────────────────────────────────────────────────────────────

@Preview(
    showBackground = true,
    backgroundColor = 0xFF0D1117,
    showSystemUi = true,
    name = "Home — Repositories Tab",
)
@Composable
private fun PreviewHomeRepos() {
    DevRankTheme {
        HomeLeaderboardScreen()
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF0D1117,
    showSystemUi = true,
    name = "Home — Developers Tab (preview override)",
)
@Composable
private fun PreviewHomeDevelopers() {
    DevRankTheme {
        // Tab 1 hardcoded for preview
        var tab by remember { mutableStateOf(1) }
        HomeLeaderboardScreen()
    }
}