package com.kundutechstudio.ranks.presentation.rank_screen.top_starred_repo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kundutechstudio.theme.Components.Navigationcomponents.DevRankTopBar
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.IconSize
import com.kundutechstudio.theme.ui.TextMuted
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TopStarredRepoListRootScreen(
    viewModel: TopStarredRepoListViewModel = koinViewModel(),
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TopStarredRepoListScreen(
        state = state,
        onAction = viewModel::onAction,
        onBack = onBack
    )
}

@Composable
private fun TopStarredRepoListScreen(
    state: TopStarredRepoListState,
    onAction: (TopStarredRepoListAction) -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DevRankTopBar(
            title = "⭐ Top Starred",
            showBack = true,
            onBack = onBack
        )

    }

    LazyColumn {
     //   LeaderboardRow
    }
}

@Preview
@Composable
private fun Preview() {
    DevRankTheme {
        TopStarredRepoListScreen(
            state = TopStarredRepoListState(),
            onAction = {},
            onBack = {},
        )
    }
}