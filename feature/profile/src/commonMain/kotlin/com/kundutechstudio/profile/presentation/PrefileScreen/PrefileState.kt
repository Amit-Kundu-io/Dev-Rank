package com.kundutechstudio.profile.presentation.PrefileScreen

import androidx.compose.runtime.Immutable
import com.kundutechstudio.profile.data.Models.ContributionResponse.Repository

@Immutable
data class PrefileState(

    // Profile
    val userId: String = "",
    val username: String = "",
    val name: String = "",
    val avatarUrl: String? = null,
    val bio: String = "",
    val profileUrl: String = "",
    val company: String = "",
    val location: String = "",
    val websiteUrl: String = "",
    val createdAt: String = "",

    // Social
    val followers: Int = 0,
    val following: Int = 0,

    // Repositories
    val totalRepositories: Int = 0,
    val totalStars: Int = 0,
    val totalForks: Int = 0,
    val repositories: List<Repository> = emptyList(),


    // Selected year
    val selectedYear: Int = 2026,

    // Contributions
    val totalContributions: Int = 0,
    val totalCommits: Int = 0,
    val totalIssues: Int = 0,
    val totalPullRequests: Int = 0,
    val totalPullRequestReviews: Int = 0,
    val totalRepositoryContributions: Int = 0,

    // Contributed repositories
    val repositoriesWithCommits: Int = 0,
    val repositoriesWithIssues: Int = 0,
    val repositoriesWithPullRequests: Int = 0,
    val repositoriesWithPullRequestReviews: Int = 0,

    // Heatmap
    val contributionData: List<List<Int>> = emptyList(),

    // UI
    val isLoading: Boolean = true,
    val error: String? = null,

    val isLogin : Boolean = true,
)