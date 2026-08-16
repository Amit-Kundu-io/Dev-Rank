/**
 * CompareNavigation.kt
 *
 * Author      : Amit Kundu
 * Created On  : 16/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.amit_kundu_io.compare.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.amit_kundu_io.compare.presentation.CompareRootScreen


fun NavGraphBuilder.compareNavigation(navController: NavHostController, ) {

    navigation< CompareRoutes.CompareGraph>(startDestination = CompareRoutes.CompareRoute) {

        composable<CompareRoutes.CompareRoute> {
            CompareRootScreen()
        }
    }
}