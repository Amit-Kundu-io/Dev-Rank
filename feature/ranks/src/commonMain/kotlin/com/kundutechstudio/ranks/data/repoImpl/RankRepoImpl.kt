package com.kundutechstudio.ranks.data.repoImpl

import com.kundutechstudio.ranks.data.Apis.Apis
import com.kundutechstudio.ranks.data.models.RepoResponse.RepoResponse
import com.kundutechstudio.ranks.domain.repo.RankRepo

class RankRepoImpl(
    apis: Apis
) : RankRepo {
    override suspend fun getTopStarredRepos(
        q: String,
        sort: String,
        order: String,
        per_page: String
    ): RepoResponse {
        TODO("Not yet implemented")
    }
}