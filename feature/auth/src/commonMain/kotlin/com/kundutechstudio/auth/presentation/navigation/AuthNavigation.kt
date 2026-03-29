package com.kundutechstudio.auth.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.kundutechstudio.auth.presentation.login.LoginAction
import com.kundutechstudio.auth.presentation.login.LoginRootScreen
import com.kundutechstudio.auth.presentation.login.LoginViewModel
import com.kundutechstudio.auth.presentation.splash.SplashRootScreen
import com.kunduthchstudio.utility.Logger.Logger
import com.kunduthchstudio.utility.REDIRECT_URI
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.authNavigation(
    navController: NavController,
    navigateToDashboard: () -> Unit
) {
    Logger.d("DEV_RANK_AUTH", "Step 1: authNavigation initialized")

    navigation<AuthRouts.AuthGraph>(startDestination = AuthRouts.SplashRoute()) {

        composable<AuthRouts.SplashRoute> {
            SplashRootScreen(
                onLoginScreen = {
                   // navController.navigate(AuthRouts.LoginRoute())

                    navigateToDashboard()
                },
                onDashboardScreen = {
                    navigateToDashboard()
                }
            )
        }

        composable<AuthRouts.LoginRoute>(
            deepLinks = listOf(
                navDeepLink { uriPattern = "$REDIRECT_URI?code={code}" }
            )
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<AuthRouts.LoginRoute>()
            val viewModel: LoginViewModel = koinViewModel()

            Logger.d(
                "DEV_RANK_AUTH",
                "Step 2: LoginRoute composed. Code from route: ${route.code}"
            )

            LaunchedEffect(route.code) {
                Logger.d(
                    "DEV_RANK_AUTH",
                    "Step 3: LaunchedEffect triggered with code: ${route.code}"
                )
                if (!route.code.isNullOrEmpty()) {
                    Logger.d("DEV_RANK_AUTH", "Step 3.1: Sending code to ViewModel: ${route.code}")
                    viewModel.onAction(LoginAction.OnGithubResponse(route.code))
                } else {
                    Logger.d("DEV_RANK_AUTH", "Step 3.2: Waiting for deep link code...")
                }
            }

            LoginRootScreen(
                viewModel = viewModel,
                onLoginSuccess = { token ->
                    Logger.d("DEV_RANK_AUTH", "Step 7: onLoginSuccess callback triggered")
                    Logger.d("Authorization:", token)
                    navigateToDashboard()
                }
            )
        }
    }
}