package com.kundutechstudio.devrank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kundutechstudio.devrank.root_navigation.RootNavigation
import com.kundutechstudio.theme.ui.BgDefault
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.DevRankTheme

@Composable
fun App() {
    DevRankTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BgDefault)
        ) {
            RootNavigation()
        }
    }
}