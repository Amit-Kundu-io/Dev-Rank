package com.kundutechstudio.bottom_navigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.amit_kundu_io.compare.navigation.compareNavigation
import com.kundutechstudio.profile.presentation.navigation.profileNavigation
import com.kundutechstudio.ranks.presentation.navigation.RankRoutes
import com.kundutechstudio.ranks.presentation.navigation.rankNavigation

@Composable
fun BottomNavigation(
    navController: NavHostController,
    navigateToLoginPage: () -> Unit

) {

    NavHost(
        navController = navController,
        startDestination = RankRoutes.RankGraph,
    ) {
        rankNavigation(navController)

        profileNavigation(
            navController,
            navigateToLoginPage = navigateToLoginPage
        )

        compareNavigation(
            navController = navController
        )

    }
}