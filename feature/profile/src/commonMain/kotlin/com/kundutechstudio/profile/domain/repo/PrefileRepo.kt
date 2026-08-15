package com.kundutechstudio.profile.domain.repo

import com.kundutechstudio.profile.data.Models.ContributionResponse.GraphQLResponse


interface PrefileRepo {
    suspend fun getProfile(
        username: String? = null,
        token: String? = null,
        from: String,
        to: String
    ): GraphQLResponse
}