package com.kundutechstudio.auth.presentation.login

import androidx.compose.runtime.Immutable

@Immutable
data class LoginState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null,
    val token : String? = null
)