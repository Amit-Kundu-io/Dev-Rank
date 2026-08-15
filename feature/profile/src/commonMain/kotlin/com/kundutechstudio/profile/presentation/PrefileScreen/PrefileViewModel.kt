package com.kundutechstudio.profile.presentation.PrefileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundutechstudio.database.datastore.DRDataStore
import com.kundutechstudio.network.res.NetworkResult
import com.kundutechstudio.profile.data.Models.ContributionResponse.GitHubUser
import com.kundutechstudio.profile.data.Models.ContributionResponse.GraphQLResponse
import com.kundutechstudio.profile.domain.use_case.get_contribution_graph_use_case.GetContributionGraphUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PrefileViewModel(
    private val drDataStore: DRDataStore,
    private val getContributionGraphUseCase: GetContributionGraphUseCase
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(
        PrefileState()
    )

    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                hasLoadedInitialData = true

                checkAuthentication()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PrefileState()
        )

    fun onAction(action: PrefileAction) {
        when (action) {
            // Add actions later
            else -> {}
        }
    }


    private fun checkAuthentication() {
        viewModelScope.launch {


            val token = drDataStore.token.firstOrNull()

            // NOT LOGGED IN
            if (token.isNullOrBlank()) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        isLogin = false
                    )
                }

                return@launch
            }

            // LOGGED IN
            getContributionGraph()
        }
    }

    private suspend fun getContributionGraph() {

        getContributionGraphUseCase(
            username = null,
            from = "2026-01-01T00:00:00Z",
            to = "2026-12-31T23:59:59Z"
        ).onEach { result ->

            when (result) {

                NetworkResult.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                }

                is NetworkResult.Success -> {

                    val response = result.data
                    val user = response?.getUser()

                    if (user == null) {

                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = "GitHub user not found"
                            )
                        }

                        return@onEach
                    }

                    val contributions = user.contributionsCollection

                    val calendar = contributions.contributionCalendar

                    _state.update {

                        it.copy(

                            // Profile
                            userId = user.id,
                            username = user.login,
                            name = user.name.orEmpty(),
                            avatarUrl = user.avatarUrl,
                            bio = user.bio.orEmpty(),
                            profileUrl = user.url.orEmpty(),
                            company = user.company.orEmpty(),
                            location = user.location.orEmpty(),
                            websiteUrl = user.websiteUrl.orEmpty(),
                            createdAt = user.createdAt,

                            // Social
                            followers = user.followers.totalCount,

                            following = user.following.totalCount,

                            // Repository
                            totalRepositories = user.repositories.totalCount,

                            totalStars = user.totalStars(),

                            totalForks = user.totalForks(),

                            repositories = user.repositories.nodes,

                            // Contributions
                            totalContributions = calendar.totalContributions,

                            totalCommits = contributions.totalCommitContributions,

                            totalIssues = contributions.totalIssueContributions,

                            totalPullRequests = contributions.totalPullRequestContributions,

                            totalPullRequestReviews = contributions.totalPullRequestReviewContributions,

                            totalRepositoryContributions = contributions.totalRepositoryContributions,

                            // Contributed repositories
                            repositoriesWithCommits = contributions.totalRepositoriesWithContributedCommits,

                            repositoriesWithIssues = contributions.totalRepositoriesWithContributedIssues,

                            repositoriesWithPullRequests = contributions.totalRepositoriesWithContributedPullRequests,

                            repositoriesWithPullRequestReviews = contributions.totalRepositoriesWithContributedPullRequestReviews,

                            // Heatmap
                            contributionData =
                                response.toHeatmap(),

                            isLoading = false,
                            error = null
                        )
                    }
                }

                is NetworkResult.Error -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}

fun GraphQLResponse.getUser(): GitHubUser? {
    return data?.viewer ?: data?.user
}

fun GraphQLResponse.toHeatmap(): List<List<Int>> {

    return getUser()
        ?.contributionsCollection
        ?.contributionCalendar
        ?.weeks
        ?.map { week ->
            week.contributionDays.map { day ->
                day.contributionCount
            }
        }
        ?: emptyList()
}


fun GitHubUser.totalStars(): Int {
    return repositories.nodes.sumOf {
        it.stargazerCount
    }
}

fun GitHubUser.totalForks(): Int {
    return repositories.nodes.sumOf {
        it.forkCount
    }
}