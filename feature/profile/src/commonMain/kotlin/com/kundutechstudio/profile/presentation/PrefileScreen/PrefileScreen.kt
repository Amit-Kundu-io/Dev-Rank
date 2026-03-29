package com.kundutechstudio.profile.presentation.PrefileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kundutechstudio.theme.Components.Profilecomponents.YourRankCard
import com.kundutechstudio.theme.Components.Statcomponents.ContributionHeatmap
import com.kundutechstudio.theme.ui.Spacing
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PrefileRootScreen(
    viewModel: PrefileViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PrefileScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun PrefileScreen(
    state: PrefileState,
    onAction: (PrefileAction) -> Unit,
) {
    Column {
        YourRankCard(
            initials = "AK",
            username = "Amit Kundu",
            handle = "Amit-Kundu-io",
            globalRank = 2456,
            followers = "3.2K",
            repos = "128",
            stars = "24K",
            followersDelta = "22",
            reposDelta = "3",
            starsDelta = "140",
        )

        val fakeData = List(52) {
            List(7) { listOf(0, 1, 2, 3, 4).random() }
        }

        ContributionHeatmap(
            levels = fakeData,//state.contributionData,
            modifier = Modifier.padding(Spacing.lg),
        )

    }
}

