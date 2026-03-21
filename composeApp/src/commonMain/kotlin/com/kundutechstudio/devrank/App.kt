package com.kundutechstudio.devrank

import androidx.compose.runtime.Composable
import com.kundutechstudio.devrank.root_navigation.RootNavigation
import com.kundutechstudio.theme.ui.DevRankTheme

@Composable
fun App() {
    DevRankTheme {
        RootNavigation()
    }
}