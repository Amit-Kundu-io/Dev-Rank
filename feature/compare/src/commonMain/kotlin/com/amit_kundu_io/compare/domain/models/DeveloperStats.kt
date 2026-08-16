/**
 * DeveloperStats.kt
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

package com.amit_kundu_io.compare.domain.models


data class DeveloperStats(
    val id: String,
    val username: String,
    val name: String,
    val avatarUrl: String?,
    val bio: String?,

    // Profile
    val followers: Int,
    val following: Int,

    // Repository
    val repositoryCount: Int,
    val totalStars: Int,
    val totalForks: Int,
    val repositoryQuality: Double,

    // Contributions
    val totalContributions: Int,
    val commits: Int,
    val issues: Int,
    val pullRequests: Int,
    val pullRequestReviews: Int,

    // Activity
    val activeDays: Int,
    val activeWeeks: Int,
    val longestStreak: Int,

    // Repository details
    val originalRepositories: Int,
    val activeRepositories: Int,
    val archivedRepositories: Int,

    // Languages
    val languages: List<String>,
)