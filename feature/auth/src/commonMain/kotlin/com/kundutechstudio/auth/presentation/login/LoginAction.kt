package com.kundutechstudio.auth.presentation.login

sealed interface LoginAction {
    data object LoginClick : LoginAction

}