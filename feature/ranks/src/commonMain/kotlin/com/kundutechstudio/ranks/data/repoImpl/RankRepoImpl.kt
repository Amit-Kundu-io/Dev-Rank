package com.kundutechstudio.ranks.data.repoImpl

import com.kundutechstudio.ranks.data.Apis.Apis
import com.kundutechstudio.ranks.data.models.RepoResponse.RepoResponse
import com.kundutechstudio.ranks.domain.repo.RankRepo

class RankRepoImpl(
    private val apis: Apis
) : RankRepo {
    override suspend fun getTopStarredRepos(): RepoResponse {
        return apis.getTopStarredRepos()
    }

    override suspend fun getTrendingReposToday(
        today: String,
        authorization: String?
    ): RepoResponse {
        return apis.getTodayTrendingRepos(today, authorization)
    }

}