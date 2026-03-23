package com.kundutechstudio.ranks.domain.repo

import com.kundutechstudio.ranks.data.models.RepoResponse.RepoResponse

interface RankRepo {

    suspend fun getTopStarredRepos(): RepoResponse

    suspend fun getTrendingReposToday(
        today: String,
        authorization: String? = null
    ): RepoResponse

    suspend fun getLargestRepos(
        authorization: String? = null
    ): RepoResponse

    suspend fun getRepositories(
        query: String,
        sort: String = "stars",
        order: String = "desc",
        perPage: Int = 50,
        authorization: String? = null
    ): RepoResponse


}