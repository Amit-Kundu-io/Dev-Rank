/**
 * CalculateDeveloperScoreUseCase.kt
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

package com.amit_kundu_io.compare.domain.use_case.GetDeveloperStatsUseCase

import com.amit_kundu_io.compare.domain.models.DeveloperScore
import com.amit_kundu_io.compare.domain.models.DeveloperStats


class CalculateDeveloperScoreUseCase {

    operator fun invoke(
        developer: DeveloperStats,
        starsScore: Double,
        forksScore: Double,
        repositoryQualityScore: Double,
        contributionsScore: Double,
        issuesPullRequestsScore: Double,
        followersScore: Double,
        reviewsScore: Double,
        activityScore: Double,
    ): DeveloperScore {

        val totalScore = starsScore * 0.20 + forksScore * 0.10 + repositoryQualityScore * 0.15 + contributionsScore * 0.20 + issuesPullRequestsScore * 0.10 + followersScore * 0.05 + reviewsScore * 0.10 + activityScore * 0.10

        return DeveloperScore(

            username = developer.username,
            avatarUrl = developer.avatarUrl,

            stars = developer.totalStars,
            forks = developer.totalForks,

            repositoryQuality = developer.repositoryQuality,

            contributions = developer.totalContributions,

            commits = developer.commits,

            issues = developer.issues,

            pullRequests = developer.pullRequests,

            followers = developer.followers,

            pullRequestReviews = developer.pullRequestReviews,

            activeDays = developer.activeDays,

            activeWeeks = developer.activeWeeks,

            longestStreak = developer.longestStreak,

            starsScore = starsScore,
            forksScore = forksScore,

            repositoryQualityScore = repositoryQualityScore,

            contributionsScore = contributionsScore,

            issuesPullRequestsScore = issuesPullRequestsScore,

            followersScore = followersScore,

            reviewsScore = reviewsScore,

            activityScore = activityScore,

            totalScore = totalScore,

            grade = calculateGrade(totalScore),
        )
    }

    private fun calculateGrade(
        score: Double
    ): String {

        return when {
            score >= 90 -> "S"
            score >= 80 -> "A+"
            score >= 70 -> "A"
            score >= 60 -> "B+"
            score >= 50 -> "B"
            score >= 40 -> "C"
            else -> "D"
        }
    }
}