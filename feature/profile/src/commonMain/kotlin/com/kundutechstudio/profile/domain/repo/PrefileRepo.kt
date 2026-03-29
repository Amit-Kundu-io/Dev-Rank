package com.kundutechstudio.profile.domain.repo

import com.kundutechstudio.profile.data.Models.ContributionResponse.GraphQLResponse


interface PrefileRepo {
    suspend fun getContributionResponse(username: String,token : String?): GraphQLResponse
}