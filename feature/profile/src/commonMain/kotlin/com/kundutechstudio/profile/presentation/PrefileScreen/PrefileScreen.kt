package com.kundutechstudio.profile.presentation.PrefileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingType
import com.kundutechstudio.theme.Components.Cardcomponents.RepoVerticalCard.RepoVerticalCard
import com.kundutechstudio.theme.Components.Cardcomponents.RepoVerticalCard.RepoVerticalCardSkeleton
import com.kundutechstudio.theme.Components.Leaderboardcomponents.SectionHeader
import com.kundutechstudio.theme.Components.Profilecomponents.YourRankCard
import com.kundutechstudio.theme.Components.Statcomponents.ContributionHeatmap
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.Spacing
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PrefileRootScreen(
    navigateToLoginPage: () -> Unit,
    viewModel: PrefileViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Dev Rank",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator()

        }

    } else {

        if (state.isLogin){
            PrefileScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }
        else{
            LockScreen(
                onLoginClick = navigateToLoginPage
            )

        }


    }
}

@Composable
private fun PrefileScreen(
    state: PrefileState,
    onAction: (PrefileAction) -> Unit,
) {
    val listState = rememberLazyListState()
    Column {
        YourRankCard(
            initials = "AK",
            username = state.name,
            imageUrl = state.avatarUrl,
            bio = state.bio,
            handle = state.username,
            followers = (state.followers ?: 0).toString(),
            repos = state.totalRepositories.toString(),
            stars = state.totalStars.toString(),
        )

        LazyColumn(
            state = listState,
            modifier = Modifier.padding(Spacing.lg),
            contentPadding = PaddingValues(bottom = Spacing.huge)
        ) {

            item(
                key = "hader-contributions"
            ) {
                ContributionHeatmap(
                    count = state.totalContributions,
                    levels = state.contributionData,
                )
            }

            // Trending
            item(
                key = "hader-trending"
            ) {
                Column(
                ) {
                    SectionHeader(
                        title = "🔥 Top Repo",
                        badgeLabel = "CREATE",
                        badgeColor = AccentBlueLight,
                        badgeBg = AccentBlueGhost,
                    )
                }

            }
            if (state.isLoading) {
                items(3) {
                    RepoVerticalCardSkeleton()
                    Spacer(Modifier.height(Spacing.sm))
                }
            } else {
                items(
                    items = state.repositories.take(5),
                    key = { it.name }) { repo ->
                    RepoVerticalCard(
                        onClick = {},
                        name = repo.name,
                        description = repo.description ?: "",
                        stars = (repo.stargazerCount ?: 0).toString(),
                        language = repo.primaryLanguage?.name ?: "",
                        trendingType = if (repo.isPrivate) TrendingType.PRIVATE else TrendingType.PUBLIC,
                        trendingLabel = if (repo.isPrivate) TrendingType.PRIVATE.name else TrendingType.PUBLIC.name
                    )
                    Spacer(Modifier.height(Spacing.sm))
                }
            }
        }

    }
}

