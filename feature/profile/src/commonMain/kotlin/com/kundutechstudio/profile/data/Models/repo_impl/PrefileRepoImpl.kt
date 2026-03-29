package com.kundutechstudio.profile.data.Models.repo_impl

import com.kundutechstudio.profile.data.Models.ContributionResponse.GraphQLResponse
import com.kundutechstudio.profile.data.apis.Apis
import com.kundutechstudio.profile.domain.repo.PrefileRepo

class PrefileRepoImpl(
    private val apis: Apis
) : PrefileRepo {
    override suspend fun getContributionResponse(
        username: String,
        token: String?
    ): GraphQLResponse {
        return apis.getContributionGraph(username,token)
    }


}