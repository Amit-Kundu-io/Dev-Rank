package com.kundutechstudio.ranks.presentation.rank_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kundutechstudio.ranks.presentation.models.DevItem
import com.kundutechstudio.ranks.presentation.models.RepoItem
import com.kundutechstudio.ranks.presentation.rank_screen.Repositories.RepositoriesRootScreen
import com.kundutechstudio.theme.Components.Navigationcomponents.DevRankTabs
import com.kundutechstudio.theme.Components.Navigationcomponents.PageHeader
import com.kundutechstudio.theme.ui.BgDefault
import com.kundutechstudio.theme.ui.DevRankTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RankRootScreen(
    viewModel: RankViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RankScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun RankScreen(
    state: RankState,
    onAction: (RankAction) -> Unit,
    onRepoClick: (RepoItem) -> Unit = {},
    onDevClick: (DevItem) -> Unit = {},
    onViewAllRepos: () -> Unit = {},
    onViewAllDevs: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
) {
    var selectedTab by remember { mutableStateOf(0) }

    val tabs by lazy {
        listOf(
            "⭐ Repositories",
            "👤 Developers",
            "🔥 Activity",
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDefault),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {

        PageHeader(
            title = "Global Developer\nRankings",
        )

        DevRankTabs(
            tabs = tabs,
            selected = selectedTab,
            onSelect = { selectedTab = it },
        )


        if (selectedTab == 0) {
            RepositoriesRootScreen()

        }

    }

}

@Preview
@Composable
private fun Preview() {
    DevRankTheme {
        RankScreen(
            state = RankState(),
            onAction = {}
        )
    }
}