package com.kundutechstudio.auth.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kunduthchstudio.utility.CLIENT_ID
import com.kunduthchstudio.utility.REDIRECT_URI
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRootScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.token != null) {
        onLoginSuccess(state.token!!)
    }

    LoginScreen(
        state = state,
        onAction = viewModel::onAction,
        onLoginSuccess = onLoginSuccess
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    onLoginSuccess: (String) -> Unit
) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    val authUrl = "https://github.com/login/oauth/authorize" +
                            "?client_id=$CLIENT_ID" +
                            "&scope=read:user repo" +
                            "&redirect_uri=$REDIRECT_URI"
                    uriHandler.openUri(authUrl)
                }) {
                    Text("Login with GitHub")
                }

                state.error?.let {
                    Text(text = "Error: $it")
                }

                Button(
                    onClick = {
                        onLoginSuccess("")
                    }
                ){
                    Text("Skip Login")
                }
            }
        }
    }
}
