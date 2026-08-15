package com.kundutechstudio.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundutechstudio.auth.data.OAuthCallbackReceiver
import com.kundutechstudio.auth.data.repo_impl.AuthRepository
import com.kunduthchstudio.utility.Logger.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow(
            LoginState()
        )

    val state =
        _state.asStateFlow()

    init {

        Logger.d(
            "DEV_RANK_AUTH",
            "LOGIN VIEWMODEL CREATED"
        )

        OAuthCallbackReceiver.register { url ->

            Logger.d(
                "DEV_RANK_AUTH",
                "OAUTH CALLBACK RECEIVED"
            )

            Logger.d(
                "DEV_RANK_AUTH",
                "CALLBACK URL = $url"
            )

            val code =
                extractCode(url)

            if (code != null) {

                Logger.d(
                    "DEV_RANK_AUTH",
                    "GITHUB AUTHORIZATION CODE RECEIVED"
                )

                exchangeCodeForToken(code)

            } else {

                Logger.d(
                    "DEV_RANK_AUTH",
                    "GITHUB AUTHORIZATION CODE NOT FOUND"
                )
            }
        }
    }

    private fun extractCode(
        url: String
    ): String? {

        return try {

            val query =
                url.substringAfter(
                    "?",
                    ""
                )

            query
                .split("&")
                .firstOrNull {
                    it.startsWith("code=")
                }
                ?.substringAfter(
                    "code="
                )
                ?.takeIf {
                    it.isNotEmpty()
                }

        } catch (e: Exception) {

            Logger.d(
                "DEV_RANK_AUTH",
                "CODE EXTRACTION ERROR = ${e.message}"
            )

            null
        }
    }

    fun onAction(
        action: LoginAction
    ) {

        when (action) {

            LoginAction.LoginClick -> {

                Logger.d(
                    "DEV_RANK_AUTH",
                    "LOGIN BUTTON CLICKED"
                )
            }
        }
    }

    private fun exchangeCodeForToken(
        code: String
    ) {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            Logger.d(
                "DEV_RANK_AUTH",
                "EXCHANGING CODE FOR TOKEN"
            )

            val result = authRepository.exchangeCodeForToken(code)

            result.onSuccess { tok ->

                Logger.d("DEV_RANK_AUTH", "================================")

                Logger.d("DEV_RANK_AUTH", "GITHUB LOGIN SUCCESS")

                Logger.d("DEV_RANK_AUTH", "USER AUTHENTICATED")

                Logger.d("DEV_RANK_AUTH", "================================")

                /*
                 * Do NOT log the access token.
                 */

                _state.update {
                    it.copy(
                        token = tok,
                        isLoading = false,
                        isLoggedIn = true
                    )
                }
            }
                .onFailure { error ->

                    Logger.d("DEV_RANK_AUTH", "GITHUB LOGIN FAILED")
                    Logger.d("DEV_RANK_AUTH", "ERROR = ${error.message}")

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
        }
    }

    override fun onCleared() {

        OAuthCallbackReceiver.clear()

        super.onCleared()
    }
}