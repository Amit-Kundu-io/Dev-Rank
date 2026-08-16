/**
 * GitHubRepository.kt
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

package com.amit_kundu_io.compare.data.models.GitHubDeveloperResponse

import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepository(
    val name: String,
    val stargazerCount: Int,
    val forkCount: Int,

    val isArchived: Boolean,
    val isFork: Boolean,

    val createdAt: String,
    val updatedAt: String,

    val primaryLanguage: GitHubLanguage? = null,
)

@Serializable
data class GitHubLanguage(
    val name: String,
)