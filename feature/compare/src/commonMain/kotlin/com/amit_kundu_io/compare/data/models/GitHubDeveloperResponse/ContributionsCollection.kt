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

package com.amit_kundu_io.compare.data.models.GitHubDeveloperResponse

import kotlinx.serialization.Serializable

@Serializable
data class ContributionsCollection(
    val totalCommitContributions: Int,
    val totalIssueContributions: Int,
    val totalPullRequestContributions: Int,
    val totalPullRequestReviewContributions: Int,
    val totalRepositoryContributions: Int,

    val totalRepositoriesWithContributedCommits: Int,
    val totalRepositoriesWithContributedIssues: Int,
    val totalRepositoriesWithContributedPullRequests: Int,
    val totalRepositoriesWithContributedPullRequestReviews: Int,

    val contributionCalendar: ContributionCalendar,
)