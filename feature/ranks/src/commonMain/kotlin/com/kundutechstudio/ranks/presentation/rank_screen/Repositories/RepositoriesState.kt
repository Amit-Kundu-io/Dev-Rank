package com.kundutechstudio.ranks.presentation.rank_screen.Repositories

import androidx.compose.runtime.Immutable
import com.kundutechstudio.ranks.domain.dao.RepoGrowthDAO
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO
import com.kundutechstudio.ranks.domain.use_case.get_largest_repos_use_case.GetLargestReposUseCase

@Immutable
data class RepositoriesState(
    val isLoading: Boolean = false,
    val error : String = "",

    val isStarredRepoLoading: Boolean = true,
    val isTopRepoLoading: Boolean = true,
    val isLargestRepoLoading: Boolean = true,

    val topStarredRepoList: List<RepoItemDAO> = emptyList(),
    val topTrendingRepoList: List<RepoItemDAO> = emptyList(),
    val topLargestRepoList: List<RepoItemDAO> = emptyList(),
)

