/**
 * ContributionsCollection.kt
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
data class ContributionsCollection(
    val totalCommitContributions: Int = 0,
    val totalIssueContributions: Int = 0,
    val totalPullRequestContributions: Int = 0,
    val totalPullRequestReviewContributions: Int = 0,
    val totalRepositoryContributions: Int = 0,

    val totalRepositoriesWithContributedCommits: Int = 0,
    val totalRepositoriesWithContributedIssues: Int = 0,
    val totalRepositoriesWithContributedPullRequests: Int = 0,
    val totalRepositoriesWithContributedPullRequestReviews: Int = 0,

    val contributionCalendar: ContributionCalendar
)