package com.kundutechstudio.ranks.presentation.rank_screen.Repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundutechstudio.network.res.NetworkResult
import com.kundutechstudio.ranks.domain.use_case.get_top_Treanding_repo_use_case.GetTopTrendingRepoUseCase
import com.kundutechstudio.ranks.domain.use_case.get_top_starred_repo_use_case.GetTopStarredRepoUseCase
import com.kunduthchstudio.utility.GlobalUtility
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RepositoriesViewModel(
    private val getTopStarredRepoUseCase: GetTopStarredRepoUseCase,
    private val getTopTrendingRepoUseCase: GetTopTrendingRepoUseCase,
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RepositoriesState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                getTopStarredRepo()
                getTopTrendingRepo()
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

    private fun getTopStarredRepo() {
        getTopStarredRepoUseCase.invoke().onEach { res ->
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

        }.launchIn(viewModelScope)
    }



    private fun getTopTrendingRepo() {
        val today = GlobalUtility.getLast7DaysDate()
        getTopTrendingRepoUseCase.invoke(today).onEach { res ->
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

        }.launchIn(viewModelScope)
    }

}