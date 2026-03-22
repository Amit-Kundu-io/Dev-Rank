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

    override suspend fun getLargestRepos(authorization: String?): RepoResponse {
        return apis.getLargestRepos(authorization)
    }

    override suspend fun getRepositories(
        query: String,
        sort: String,
        order: String,
        perPage: Int,
        authorization: String?
    ): RepoResponse {
        return apis.getRepositories(
            query = query,
            sort = sort,
            order = order,
            perPage = perPage,
            authorization = authorization
        )
    }

}