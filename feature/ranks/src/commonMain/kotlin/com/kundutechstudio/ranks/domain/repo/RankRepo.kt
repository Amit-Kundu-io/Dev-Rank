package com.kundutechstudio.ranks.domain.repo

import com.kundutechstudio.ranks.data.models.RepoResponse.RepoResponse

interface RankRepo {

    suspend fun getTopStarredRepos(): RepoResponse
}