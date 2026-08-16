/**
 * CompareViewModel.kt
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

package com.amit_kundu_io.compare.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amit_kundu_io.compare.domain.use_case.GetDeveloperStatsUseCase.CompareDevelopersUseCase
import com.kundutechstudio.database.datastore.DRDataStore
import com.kundutechstudio.network.res.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CompareViewModel(
    private val drDataStore: DRDataStore,
    private val compareDevelopersUseCase: CompareDevelopersUseCase,
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(CompareState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CompareState()
        )

    fun onAction(action: CompareAction) {

        when (action) {

            is CompareAction.UsernameAChanged -> {
                _state.update {
                    it.copy(
                        usernameA = action.value
                    )
                }
            }

            is CompareAction.UsernameBChanged -> {
                _state.update {
                    it.copy(
                        usernameB = action.value
                    )
                }
            }

            CompareAction.Compare -> {
                compare()
            }
        }
    }

    private fun compare() {

        viewModelScope.launch {

            val usernameA = state.value.usernameA.trim()

            val usernameB = state.value.usernameB.trim()

            if (usernameA.isBlank() || usernameB.isBlank()) {
                _state.update {
                    it.copy(
                        error = "Enter both GitHub usernames"
                    )
                }

                return@launch
            }

            val token = drDataStore.token.firstOrNull()

            if (token.isNullOrBlank()) {
                _state.update {
                    it.copy(error = "GitHub login required")
                }

                return@launch
            }

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

              compareDevelopersUseCase(usernameA = "Amit-Kundu-io", usernameB = "DevMeghaG", token = token).onEach { result ->

                  when (result) {

                      is NetworkResult.Success -> {
                          _state.update {
                              it.copy(
                                  isLoading = false,
                                  comparison = result.data,
                                  error = null,
                              )
                          }
                      }

                      is NetworkResult.Error -> {
                          _state.update {
                              it.copy(
                                  isLoading = false,
                                  error = result.message,
                              )
                          }
                      }

                      NetworkResult.Loading -> Unit
                  }

              }.launchIn(viewModelScope)




        }
    }
}