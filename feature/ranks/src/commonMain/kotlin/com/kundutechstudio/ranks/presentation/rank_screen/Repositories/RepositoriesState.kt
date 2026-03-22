package com.kundutechstudio.ranks.presentation.rank_screen.Repositories

import androidx.compose.runtime.Immutable
import com.kundutechstudio.ranks.data.models.RepoResponse.TopStarredRepoItem
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO

@Immutable
data class RepositoriesState(
    val isLoading: Boolean = false,
    val error : String = "",
    val isStarredRepoLoading: Boolean = false,
    val topStarredRepoList: List<RepoItemDAO> = emptyList()
)

