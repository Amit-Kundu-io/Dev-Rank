package com.kundutechstudio.profile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kundutechstudio.profile.presentation.PrefileScreen.PrefileRootScreen

fun NavGraphBuilder.profileNavigation(navController: NavHostController) {

    navigation<ProfileRoutes.ProfileGraph>(startDestination = ProfileRoutes.ProfileRoute) {

        composable<ProfileRoutes.ProfileRoute> {
            PrefileRootScreen()
        }
    }
}