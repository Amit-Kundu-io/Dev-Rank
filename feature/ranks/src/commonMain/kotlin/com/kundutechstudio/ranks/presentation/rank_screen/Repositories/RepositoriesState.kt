package com.kundutechstudio.ranks.presentation.rank_screen.Repositories

import androidx.compose.runtime.Immutable
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO

@Immutable
data class RepositoriesState(
    val isLoading: Boolean = false,
    val error : String = "",

    val isStarredRepoLoading: Boolean = false,
    val isTopRepoLoading: Boolean = false,
    val isLargestRepoLoading: Boolean = false,
    val isBeginnerLoading: Boolean = false,
    val isActiveRepoLoading: Boolean = false,

    val topStarredRepoList: List<RepoItemDAO> = emptyList(),
    val topTrendingRepoList: List<RepoItemDAO> = emptyList(),
    val topLargestRepoList: List<RepoItemDAO> = emptyList(),
    val beginnerFriendlyRepoList: List<RepoItemDAO> = emptyList(),
    val activeRepoList: List<RepoItemDAO> = emptyList(),
)

