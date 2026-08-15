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
import com.kunduthchstudio.utility.GitHubConfig
import com.kunduthchstudio.utility.GitHubConfig.REDIRECT_URI
import com.kunduthchstudio.utility.Logger.Logger
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.authNavigation(
    navController: NavController,
    navigateToDashboard: () -> Unit
) {
    Logger.d("DEV_RANK_AUTH", "----- ini  completed")

    navigation<AuthRouts.AuthGraph>(
        startDestination = AuthRouts.SplashRoute()
    ) {

        composable<AuthRouts.SplashRoute> {

            SplashRootScreen(

                onLoginScreen = {

                    navController.navigate(
                        AuthRouts.LoginRoute()
                    )
                },

                onDashboardScreen = {
                    navigateToDashboard()
                }
            )
        }

        composable<AuthRouts.LoginRoute>(
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "devrank://oauth/callback?code={code}"
                }
            )
        ) { backStackEntry ->

            Logger.d(
                "DEV_RANK_AUTH",
                "LOGIN ROUTE COMPOSED"
            )

            val route =
                backStackEntry.toRoute<AuthRouts.LoginRoute>()

            Logger.d(
                "DEV_RANK_AUTH",
                "CALLBACK CODE = ${route.code}"
            )

            val viewModel: LoginViewModel =
                koinViewModel()

            LaunchedEffect(route.code) {

                Logger.d(
                    "DEV_RANK_AUTH",
                    "DEEPLINK LaunchedEffect"
                )

                if (!route.code.isNullOrEmpty()) {

                    Logger.d("DEV_RANK_AUTH", "STEP 5: CALLBACK RECEIVED")

                    Logger.d("DEV_RANK_AUTH", "STEP 6: CODE = ${route.code}")


                } else {

                    Logger.d("DEV_RANK_AUTH", "NO CODE IN CALLBACK")
                }
            }

            LoginRootScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    Logger.d(
                        "DEV_RANK_AUTH",
                        "NAVIGATING TO DASHBOARD"
                    )

                    navigateToDashboard()
                }
            )
        }
    }
}