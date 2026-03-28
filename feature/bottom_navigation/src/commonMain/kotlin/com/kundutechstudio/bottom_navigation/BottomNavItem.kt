package com.kundutechstudio.bottom_navigation

import com.kundutechstudio.profile.presentation.navigation.ProfileRoutes
import com.kundutechstudio.ranks.presentation.navigation.RankRoutes
import common.resources.Res
import common.resources.ic_home
import common.resources.outline_person_24
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

    data object Prefile : BottomNavItem(
        route = ProfileRoutes.ProfileGraph::class.qualifiedName,
        label = "Prefile",
        selectedIcon = Res.drawable.outline_person_24,
    )

}


val bottomNavItems = listOf(
    BottomNavItem.Rank,
    BottomNavItem.Prefile,
)


