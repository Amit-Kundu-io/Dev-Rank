package com.kundutechstudio.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundutechstudio.auth.data.repo_impl.AuthRepository
import com.kunduthchstudio.utility.Logger.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnGithubResponse -> {
                exchangeCodeForToken(action.code)
            }
            LoginAction.OnLoginClick -> {
            }
        }
    }

    private fun exchangeCodeForToken(code: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val token = authRepository.exchangeCodeForToken(code)
                if (token.isNotEmpty()) {
                    // Get User Data using the token
                    val userData = authRepository.getUserData(token)
                    
                    _state.update { it.copy(isLoading = false, token = token) }
                    
                    // Logs as requested
                    Logger.d("Authorization:", token)
                    Logger.d("DEV_RANK_AUTH", "User Data: $userData")
                } else {
                    _state.update { it.copy(isLoading = false, error = "Failed to obtain access token") }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
                Logger.d("DEV_RANK_AUTH", "Error: ${e.message}")
            }
        }
    }
}