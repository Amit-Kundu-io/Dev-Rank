/**
 * DeveloperScore.kt
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


data class DeveloperScore(
    val username: String,
    val avatarUrl: String?,

    // Raw values
    val stars: Int,
    val forks: Int,
    val repositoryQuality: Double,

    val contributions: Int,
    val commits: Int,

    val issues: Int,
    val pullRequests: Int,

    val followers: Int,
    val pullRequestReviews: Int,

    val activeDays: Int,
    val activeWeeks: Int,
    val longestStreak: Int,

    // Normalized scores 0 - 100
    val starsScore: Double,
    val forksScore: Double,
    val repositoryQualityScore: Double,
    val contributionsScore: Double,
    val issuesPullRequestsScore: Double,
    val followersScore: Double,
    val reviewsScore: Double,
    val activityScore: Double,

    // Final
    val totalScore: Double,
    val grade: String,
)