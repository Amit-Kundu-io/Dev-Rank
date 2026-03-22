package com.kundutechstudio.ranks.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kundutechstudio.ranks.presentation.rank_screen.RankRootScreen
import com.kundutechstudio.ranks.presentation.rank_screen.top_starred_repo_list.TopStarredRepoListRootScreen


fun NavGraphBuilder.rankNavigation(
    navController: NavController
) {
    navigation<RankRoutes.RankGraph>(startDestination = RankRoutes.RankRoute) {

        composable<RankRoutes.TopStarredRepoListRoute> {
            TopStarredRepoListRootScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<RankRoutes.RankRoute> {
            RankRootScreen(
                onViewAllRepos = {
                    navController.navigate(RankRoutes.TopStarredRepoListRoute)
                }
            )
        }

    }
}