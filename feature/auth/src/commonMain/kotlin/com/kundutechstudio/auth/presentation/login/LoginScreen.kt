package com.kundutechstudio.auth.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kunduthchstudio.utility.GitHubConfig
import com.kunduthchstudio.utility.GitHubConfig.CLIENT_ID
import com.kunduthchstudio.utility.GitHubConfig.REDIRECT_URI
import com.kunduthchstudio.utility.Logger.Logger
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun LoginRootScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val uriHandler = LocalUriHandler.current

    LaunchedEffect(
        state.isLoggedIn
    ) {

        if (state.isLoggedIn) {

            Logger.d("DEV_RANK_AUTH", "LOGIN SCREEN: LOGIN COMPLETED")

            //onLoginSuccess()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (state.isLoading) {

            CircularProgressIndicator()

        }
        else if (state.isLoggedIn){
            state.token?.let {
                Text(it)
            }
        }
        else {

            Column(
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = {

                        viewModel.onAction(
                            LoginAction.LoginClick
                        )

                        val authUrl = buildGitHubAuthUrl()

                        Logger.d("DEV_RANK_AUTH", "OPENING GITHUB LOGIN")
                        Logger.d("DEV_RANK_AUTH", "AUTH URL = $authUrl")

                        uriHandler.openUri(authUrl)
                    }
                ) {

                    Text("Login with GitHub")
                }

                state.error?.let {

                    Text(
                        text = it
                    )
                }
            }
        }
    }
}

private fun buildGitHubAuthUrl(): String {

    return buildString {
        append(GitHubConfig.AUTHORIZE_URL)
        append("?client_id=")
        append(GitHubConfig.CLIENT_ID)
        append("&scope=")
        append("read%3Auser%20repo")
        append("&redirect_uri=")
        append(GitHubConfig.REDIRECT_URI)
    }
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
