package com.kundutechstudio.bottom_navigation

import com.kundutechstudio.ranks.presentation.navigation.RankRoutes
import common.resources.Res
import common.resources.ic_home
import org.jetbrains.compose.resources.DrawableResource


sealed class BottomNavItem(
    val route: String?,
    val label: String,
    val selectedIcon: DrawableResource,
) {

    data object Rank : BottomNavItem(
        route = RankRoutes.RankGraph::class.qualifiedName,
        label = "Home",
        selectedIcon = Res.drawable.ic_home,
    )

}


val bottomNavItems = listOf(
    BottomNavItem.Rank,
)


