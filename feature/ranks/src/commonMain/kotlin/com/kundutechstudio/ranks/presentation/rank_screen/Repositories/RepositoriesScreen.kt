package com.kundutechstudio.ranks.presentation.rank_screen.Repositories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO
import com.kundutechstudio.ranks.presentation.models.DevItem
import com.kundutechstudio.ranks.presentation.models.RepoVerticalCard
import com.kundutechstudio.ranks.presentation.models.largestRepos
import com.kundutechstudio.ranks.presentation.models.trendingRepos
import com.kundutechstudio.theme.Components.Cardcomponents.RepoHorizontalCard
import com.kundutechstudio.theme.Components.Cardcomponents.RepoHorizontalCardSkeleton
import com.kundutechstudio.theme.Components.Leaderboardcomponents.SectionHeader
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.BgDefault
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.StarYellow
import com.kundutechstudio.theme.ui.StarYellowGhost
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RepositoriesRootScreen(
    viewModel: RepositoriesViewModel = koinViewModel(),
    onViewAllRepos: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RepositoriesScreen(
        state = state,
        onAction = viewModel::onAction,
        onViewAllRepos = onViewAllRepos
    )
}

@Composable
private fun RepositoriesScreen(
    state: RepositoriesState,
    onAction: (RepositoriesAction) -> Unit,
    onRepoClick: (RepoItemDAO) -> Unit = {},
    onDevClick: (DevItem) -> Unit = {},
    onViewAllRepos: () -> Unit = {},
    onViewAllDevs: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDefault),
        contentPadding = PaddingValues(bottom = CardSize.navBarHeight + Spacing.lg),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {

        // Most Starred
        item {
            Spacer(Modifier.height(Spacing.lg))
            SectionHeader(
                title = "⭐ Top Starred",
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
                if (state.isStarredRepoLoading) {

                    items(3) {
                        RepoHorizontalCardSkeleton()
                    }

                } else {
                    items(
                        items = state.topStarredRepoList.take(5),
                        key = { it.name }
                    ) { repo ->
                        RepoHorizontalCard(
                            repoName = repo.name,
                            ownerName = repo.owner,
                            description = repo.description,
                            stars = repo.stars,
                            language = repo.language,
                            langColor = repo.langColor,
                            rank = repo.rank,
                            onClick = { onRepoClick(repo) },
                            isLoading = state.isStarredRepoLoading
                        )
                    }
                }
            }
            Spacer(Modifier.height(Spacing.lg))
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
        item { Spacer(Modifier.height(8.dp)) }

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

}

@Preview
@Composable
private fun Preview() {
    DevRankTheme {
        RepositoriesScreen(
            state = RepositoriesState(),
            onAction = {}
        )
    }
}