/**
 * Repository.kt
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
data class Repository(
    val name: String,
    val description: String? = null,
    val stargazerCount: Int = 0,
    val forkCount: Int = 0,
    val updatedAt: String? = null,
    val isPrivate: Boolean = false,
    val primaryLanguage: PrimaryLanguage? = null
)