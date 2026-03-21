package com.kundutechstudio.ranks.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.rankNavigation(
    navController: NavController
) {
    navigation<RankRoutes.RankGraph>(startDestination = RankRoutes.RankRoute) {

        composable<RankRoutes.RankRoute> {
            Column {
                Text("Rank")
            }
        }

    }
}