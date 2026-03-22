package com.kundutechstudio.ranks.presentation.rank_screen.Repositories

import androidx.compose.runtime.Immutable
import com.kundutechstudio.ranks.domain.dao.RepoGrowthDAO
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO

@Immutable
data class RepositoriesState(
    val isLoading: Boolean = false,
    val error : String = "",
    val isStarredRepoLoading: Boolean = true,
    val isTopRepoLoading: Boolean = true,
    val topStarredRepoList: List<RepoItemDAO> = emptyList(),
    val topTrendingRepoList: List<RepoItemDAO> = emptyList(),
)

