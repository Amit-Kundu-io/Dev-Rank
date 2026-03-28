package com.kundutechstudio.ranks.presentation.rank_screen.Repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundutechstudio.network.res.NetworkResult
import com.kundutechstudio.ranks.domain.use_case.get_active_repo_use_case.GetActiveRepoUseCase
import com.kundutechstudio.ranks.domain.use_case.get_beginner_friendly_use_case.GetBeginnerFriendlyUseCase
import com.kundutechstudio.ranks.domain.use_case.get_largest_repos_use_case.GetLargestReposUseCase
import com.kundutechstudio.ranks.domain.use_case.get_top_Treanding_repo_use_case.GetTopTrendingRepoUseCase
import com.kundutechstudio.ranks.domain.use_case.get_top_starred_repo_use_case.GetTopStarredRepoUseCase
import com.kunduthchstudio.utility.GlobalUtility
import com.kunduthchstudio.utility.Logger.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepositoriesViewModel(
    private val getTopStarredRepoUseCase: GetTopStarredRepoUseCase,
    private val getTopTrendingRepoUseCase: GetTopTrendingRepoUseCase,
    private val getLargestReposUseCase: GetLargestReposUseCase,
    private val getBeginnerFriendlyUseCase: GetBeginnerFriendlyUseCase,
    private val getActiveRepoUseCase: GetActiveRepoUseCase,
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RepositoriesState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                initData()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RepositoriesState()
        )

    fun onAction(action: RepositoriesAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

    fun initData() {
        //One by one Call because of GitHub api call rate limit
        viewModelScope.launch {
            delay(5_000)
          //getTopStarredRepo()
           // getTopTrendingRepo()
           // getLargestTrendingRepo()
            getActiveRepo()
        }
    }

    private suspend fun getTopStarredRepo() {
        getTopStarredRepoUseCase.invoke().collect { res ->
            when (res) {
                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            error = res.message,
                            isStarredRepoLoading = false
                        )
                    }
                }

                NetworkResult.Loading -> {
                    _state.update {
                        it.copy(
                            isStarredRepoLoading = true
                        )
                    }
                }
                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            topStarredRepoList = res.data ?: emptyList(),
                            isStarredRepoLoading = false
                        )
                    }
                }
            }

        }
    }

    private suspend fun getTopTrendingRepo() {
        val today = GlobalUtility.getLast7DaysDate()
        getTopTrendingRepoUseCase.invoke(today).collect { res ->
            when (res) {
                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            error = res.message,
                            isTopRepoLoading = false
                        )
                    }
                }

                NetworkResult.Loading -> {
                    _state.update {
                        it.copy(
                            isTopRepoLoading = true
                        )
                    }
                }

                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            topTrendingRepoList = res.data ?: emptyList(),
                            isTopRepoLoading = false
                        )
                    }
                }
            }

        }
    }

    private suspend fun getLargestTrendingRepo() {
        getLargestReposUseCase.invoke().collect { res ->
            when (res) {
                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            error = res.message,
                            isLargestRepoLoading = false
                        )
                    }
                }

                NetworkResult.Loading -> {
                    _state.update {
                        it.copy(
                            isLargestRepoLoading = true
                        )
                    }
                }

                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            topLargestRepoList = res.data ?: emptyList(),
                            isLargestRepoLoading = false
                        )
                    }
                }
            }

        }
    }

    private suspend fun getActiveRepo() {
        getActiveRepoUseCase.invoke().collect { res ->
            when (res) {
                is NetworkResult.Error -> {
                    Logger.d("BEBUGGING",res.message)
                    _state.update {
                        it.copy(
                            error = res.message,
                            isActiveRepoLoading = false
                        )
                    }
                }

                NetworkResult.Loading -> {
                    _state.update {
                        it.copy(
                            isActiveRepoLoading = true
                        )
                    }
                }

                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            activeRepoList = res.data ?: emptyList(),
                            isActiveRepoLoading = false
                        )
                    }
                }
            }

        }
    }

    private suspend fun getBeginnerFriendly() {
        getBeginnerFriendlyUseCase.invoke().collect { res ->
            when (res) {
                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            error = res.message,
                            isBeginnerLoading = false
                        )
                    }
                }

                NetworkResult.Loading -> {
                    _state.update {
                        it.copy(
                            isBeginnerLoading = true
                        )
                    }
                }

                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            beginnerFriendlyRepoList = res.data ?: emptyList(),
                            isBeginnerLoading = false
                        )
                    }
                }
            }

        }
    }

}