package com.kundutechstudio.auth.presentation.splash

import androidx.compose.runtime.Immutable

@Immutable
data class SplashState(
    val isLoading: Boolean = false,
    val isLogin: Boolean = false,
)