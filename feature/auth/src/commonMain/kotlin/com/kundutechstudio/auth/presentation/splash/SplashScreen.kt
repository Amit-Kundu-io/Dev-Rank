package com.kundutechstudio.auth.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kundutechstudio.theme.ui.DevRankTheme
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashRootScreen(
    viewModel: SplashViewModel = koinViewModel(),
    onLoginScreen: () -> Unit,
    onDashboardScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {

        delay(2_000)

        if (state.isLogin) {
            onDashboardScreen.invoke()
        } else {
            onLoginScreen.invoke()
        }
    }

    SplashScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun SplashScreen(
    state: SplashState,
    onAction: (SplashAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Splash Screen")

    }

}

@Preview
@Composable
private fun Preview() {
    DevRankTheme {
        SplashScreen(
            state = SplashState(),
            onAction = {}
        )
    }
}