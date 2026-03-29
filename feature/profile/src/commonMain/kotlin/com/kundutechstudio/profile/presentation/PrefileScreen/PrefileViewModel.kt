package com.kundutechstudio.profile.presentation.PrefileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundutechstudio.network.res.NetworkResult
import com.kundutechstudio.profile.domain.use_case.get_contribution_graph_use_case.GetContributionGraphUseCase
import com.kunduthchstudio.utility.Logger.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlin.collections.emptyList

class PrefileViewModel(

    private val getContributionGraphUseCase: GetContributionGraphUseCase
) : ViewModel() {


    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(PrefileState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                getContributionGraph()
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PrefileState()
        )

    fun onAction(action: PrefileAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

    suspend fun getContributionGraph() {
        getContributionGraphUseCase("Amit-kundu-io").collect { res ->
            when (res) {
                is NetworkResult.Error -> {
                    Logger.d("PROFILE_ERROR", res.message)
                }
                NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            contributionData = res.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }

}