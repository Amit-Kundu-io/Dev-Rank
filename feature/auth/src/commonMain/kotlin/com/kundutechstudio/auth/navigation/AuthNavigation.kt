package com.kundutechstudio.auth.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.coroutines.delay

fun NavGraphBuilder.authNavigation(
    navController: NavController,
    navigateToDashboard: () -> Unit
) {
    navigation<AuthRouts.AuthGraph>(startDestination = AuthRouts.SplashRoute) {

        composable<AuthRouts.SplashRoute> {
            Column {
                LaunchedEffect(
                    Unit
                ) {
                    delay(1_000)
                    navigateToDashboard.invoke()
                }
                Text("Splash")
            }
        }
    }
}