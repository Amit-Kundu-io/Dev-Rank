/**
 * GraphQLData.kt
 *
 * Author      : Amit Kundu
 * Created On  : 16/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.kundutechstudio.profile.data.Models.ContributionResponse

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLData(
    val viewer: GitHubUser? = null,
    val user: GitHubUser? = null
)