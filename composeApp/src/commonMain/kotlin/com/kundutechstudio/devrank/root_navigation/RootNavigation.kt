package com.kundutechstudio.devrank.root_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kundutechstudio.auth.presentation.navigation.AuthRouts
import com.kundutechstudio.auth.presentation.navigation.authNavigation
import com.kundutechstudio.bottom_navigation.BottomScreen
import com.kundutechstudio.bottom_navigation.navigation.BottomRoutes


@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthRouts.AuthGraph,
    ) {
        authNavigation(
            navController,
            navigateToDashboard = {
                navController.navigate(BottomRoutes.BottomGraph) {
                    popUpTo(AuthRouts.AuthGraph) { inclusive = true }
                }
            })

        composable<BottomRoutes.BottomGraph> {
            BottomScreen()
        }
    }
}