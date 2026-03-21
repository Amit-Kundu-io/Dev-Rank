package com.kundutechstudio.devrank

import androidx.compose.ui.window.ComposeUIViewController
import com.kundutechstudio.devrank.root_di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }