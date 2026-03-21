package com.kundutechstudio.theme.ui

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@Composable
actual fun SetDarkStatusBar() {
    val view = LocalView.current

    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = Color(0xFF161B22).toArgb()

        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = false
    }
}