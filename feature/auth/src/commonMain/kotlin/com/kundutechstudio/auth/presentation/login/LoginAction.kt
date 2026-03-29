package com.kundutechstudio.auth.presentation.login

sealed interface LoginAction {
    data object OnLoginClick : LoginAction
    data class OnGithubResponse(val code: String) : LoginAction
}