package com.kundutechstudio.auth.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object AuthRouts {
    @Serializable
    object AuthGraph

    @Serializable
    class SplashRoute

    @Serializable
    data class LoginRoute(val code: String? = null)
}