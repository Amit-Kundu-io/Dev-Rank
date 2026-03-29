package com.kundutechstudio.auth.presentation.login

import androidx.compose.runtime.Immutable

@Immutable
data class LoginState(
    val isLoading: Boolean = false,
    val token: String? = null,
    val error: String? = null
)