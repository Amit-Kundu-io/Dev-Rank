package com.kundutechstudio.profile.data.Models.repo_impl

import com.kundutechstudio.profile.data.Models.ContributionResponse.GraphQLResponse
import com.kundutechstudio.profile.data.apis.Apis
import com.kundutechstudio.profile.domain.repo.PrefileRepo

class PrefileRepoImpl(
    private val apis: Apis
) : PrefileRepo {
    override suspend fun getProfile(
        username: String?,
        token: String?,
        from: String,
        to: String
    ): GraphQLResponse {
        return apis.getProfile(
            username = username,
            token = token,
            from = from,
            to = to
        )
    }


}