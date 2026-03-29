package com.kundutechstudio.bottom_navigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kundutechstudio.profile.presentation.navigation.profileNavigation
import com.kundutechstudio.ranks.presentation.navigation.RankRoutes
import com.kundutechstudio.ranks.presentation.navigation.rankNavigation

@Composable
fun BottomNavigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = RankRoutes.RankGraph,
    ) {
        rankNavigation(navController)
        profileNavigation(navController)
    }
}