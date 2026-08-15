/**
 * GitHubUser.kt
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
data class GitHubUser(
    val id: String,
    val login: String,
    val name: String? = null,
    val avatarUrl: String? = null,
    val bio: String? = null,
    val createdAt: String,

    val url: String? = null,
    val company: String? = null,
    val location: String? = null,
    val websiteUrl: String? = null,

    val followers: Followers,
    val following: Following,

    val repositories: RepositoryConnection,

    val contributionsCollection: ContributionsCollection
)