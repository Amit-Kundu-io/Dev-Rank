package com.kundutechstudio.profile.data.Models.ContributionResponse

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponse(
    val data: GraphQLData? = null,
    val errors: List<GraphQLError>? = null
)


@Serializable
data class GraphQLError(
    val message: String
)