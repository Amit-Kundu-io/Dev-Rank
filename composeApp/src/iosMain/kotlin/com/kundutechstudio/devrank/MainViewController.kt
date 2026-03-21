package com.kundutechstudio.devrank

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.kundutechstudio.devrank.root_di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        App()
    }
}