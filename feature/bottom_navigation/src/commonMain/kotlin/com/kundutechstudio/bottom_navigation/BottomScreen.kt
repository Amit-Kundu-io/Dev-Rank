package com.kundutechstudio.bottom_navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.amit_kundu_io.compare.navigation.CompareRoutes
import com.kundutechstudio.bottom_navigation.navigation.BottomNavigation
import com.kundutechstudio.profile.presentation.navigation.ProfileRoutes
import com.kundutechstudio.ranks.presentation.navigation.RankRoutes
import com.kundutechstudio.theme.ui.BgDefault


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun BottomScreen(
    navigateToLoginPage: () -> Unit
) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    var isShowBottomNav by remember {
        mutableStateOf(true)
    }

    var title by remember {
        mutableStateOf("")
    }
    var showBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(currentRoute) {

        isShowBottomNav = when (currentRoute) {
            RankRoutes.RankRoute::class.qualifiedName -> {
                true
            }

            ProfileRoutes.ProfileRoute::class.qualifiedName -> {
                true
            }

            CompareRoutes.CompareRoute::class.qualifiedName -> {
                true
            }

            else -> {
                false
            }
        }
    }



    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDefault)
            .statusBarsPadding()
            .navigationBarsPadding(),

        contentWindowInsets = WindowInsets(0,0,0,0),

        bottomBar = {
            AnimatedVisibility(
                visible = isShowBottomNav,
                enter = fadeIn() + slideInVertically(
                    initialOffsetY = {
                        it / 2
                    }
                ),
                exit = fadeOut() + slideOutVertically(
                    targetOffsetY = {
                        it / 2
                    }
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {

                    AppBottomNav(
                        navController = navController,
                        items = bottomNavItems
                    )
                }
            }
        }
    ) { padding ->
        Box {
            BottomNavigation(
                navController,
                navigateToLoginPage = navigateToLoginPage
            )
        }
    }
}


@Composable
fun AppBottomNav(
    navController: NavHostController,
    items: List<BottomNavItem>,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    CustomBottomBar(
        items = items,
        currentDestination = currentDestination,
        onValueChange = { item ->
            navController.navigate(item.route ?: "") {
                popUpTo(RankRoutes.RankRoute::class.qualifiedName ?: "") {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}
