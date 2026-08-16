/**
 * GetDeveloperStatsUseCase.kt
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

import com.amit_kundu_io.compare.data.models.GitHubDeveloperResponse.ContributionCalendar
import com.amit_kundu_io.compare.data.models.GitHubDeveloperResponse.GitHubRepository
import com.amit_kundu_io.compare.domain.Repository.DeveloperRepository
import com.amit_kundu_io.compare.domain.models.DeveloperStats
import com.kundutechstudio.network.res.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDeveloperStatsUseCase(
    private val repository: DeveloperRepository,
) {

    suspend operator fun invoke(
        username: String,
        token: String,
    ): NetworkResult<DeveloperStats> {

        return try {

            val response = repository.getDeveloperStats(
                username = username,
                token = token
            )

            val user = response.data?.user
                ?: return NetworkResult.Error(
                    response.errors
                        ?.firstOrNull()
                        ?.message
                        ?: "GitHub user not found"
                )

            val stats = withContext(Dispatchers.Default) {

                val repositories = user.repositories.nodes

                val totalStars = repositories.sumOf { it.stargazerCount }

                val totalForks = repositories.sumOf { it.forkCount }

                val originalRepositories = repositories.count { !it.isFork }

                val activeRepositories = repositories.count { !it.isArchived }

                val archivedRepositories = repositories.count { it.isArchived }

                val languages = repositories.mapNotNull { it.primaryLanguage?.name }.distinct()

                val calendar = user.contributionsCollection.contributionCalendar

                val activeDays = calendar.weeks.flatMap { it.contributionDays }.count { it.contributionCount > 0 }

                val activeWeeks = calendar.weeks.count { week -> week.contributionDays.any { it.contributionCount > 0 } }

                val longestStreak =
                    calculateLongestStreak(calendar)

                val repositoryQuality =
                    calculateRepositoryQuality(
                        repositories = repositories,
                        stars = totalStars,
                        forks = totalForks
                    )

                DeveloperStats(
                    id = user.id,
                    username = user.login,
                    name = user.name.orEmpty(),
                    avatarUrl = user.avatarUrl,
                    bio = user.bio,

                    followers = user.followers.totalCount,

                    following = user.following.totalCount,

                    repositoryCount = user.repositories.totalCount,

                    totalStars = totalStars,
                    totalForks = totalForks,

                    repositoryQuality = repositoryQuality,

                    totalContributions = calendar.totalContributions,

                    commits = user.contributionsCollection.totalCommitContributions,

                    issues = user.contributionsCollection .totalIssueContributions,

                    pullRequests = user.contributionsCollection.totalPullRequestContributions,

                    pullRequestReviews = user.contributionsCollection.totalPullRequestReviewContributions,

                    activeDays = activeDays,
                    activeWeeks = activeWeeks,
                    longestStreak = longestStreak,

                    originalRepositories = originalRepositories,

                    activeRepositories = activeRepositories,

                    archivedRepositories = archivedRepositories,

                    languages = languages,
                )
            }

            NetworkResult.Success(stats)

        } catch (e: Exception) {

            NetworkResult.Error(
                e.message ?: "Something went wrong"
            )
        }
    }

    private fun calculateRepositoryQuality(
        repositories: List<GitHubRepository>,
        stars: Int,
        forks: Int,
    ): Double {

        if (repositories.isEmpty()) return 0.0

        val originalRatio = repositories.count { !it.isFork }.toDouble() / repositories.size * 100.0

        val activeRatio =
            repositories.count { !it.isArchived }.toDouble() / repositories.size * 100.0

        val averageStars = stars.toDouble() / repositories.size

        val starScore = (averageStars / 1000.0 * 100.0).coerceIn(0.0, 100.0)

        val forkScore = (forks.toDouble() / repositories.size / 100.0 * 100.0).coerceIn(0.0, 100.0)

        return (originalRatio * 0.30 + activeRatio * 0.25 + starScore * 0.30 + forkScore * 0.15).coerceIn(0.0, 100.0)
    }

    private fun calculateLongestStreak(
        calendar: ContributionCalendar,
    ): Int {

        val days = calendar.weeks.flatMap { it.contributionDays }.sortedBy { it.date }

        var current = 0
        var longest = 0

        days.forEach { day ->

            if (day.contributionCount > 0) {
                current++
                longest = maxOf(longest, current)
            } else {
                current = 0
            }
        }

        return longest
    }
}
