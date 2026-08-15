package com.kundutechstudio.auth.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueDark
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentRed
import com.kundutechstudio.theme.ui.AccentRedGhost
import com.kundutechstudio.theme.ui.BgDefault
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle
import com.kunduthchstudio.utility.GitHubConfig
import com.kunduthchstudio.utility.Logger.Logger
import common.resources.Res
import common.resources.dev_rank_icon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRootScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            Logger.d("DEV_RANK_AUTH", "LOGIN SCREEN: LOGIN COMPLETED")
             onLoginSuccess()
        }
    }

    LoginScreen(
        state = state,
        onAction = viewModel::onAction,
    )
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
) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDefault),
        contentAlignment = Alignment.Center,
    ) {
        // Soft ambient glow behind the content, echoes the splash screen
        Box(
            modifier = Modifier
                .size(320.dp)
                .alpha(0.18f)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(AccentBlue, Color.Transparent),
                    ),
                    shape = CircleShape,
                )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.xxl),
        ) {
            BrandMark()

            Spacer(Modifier.height(Spacing.xxl))

            Text(
                text = "Welcome to DevRank",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(Spacing.sm))

            Text(
                text = "Discover top repos, rank top developers,\nand track your GitHub contributions.",
                style = MaterialTheme.typography.bodyMedium.copy(color = TextMuted),
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(Spacing.xxxl))

            GitHubLoginButton(
                isLoading = state.isLoading,
                onClick = {
                    onAction(LoginAction.LoginClick)

                    val authUrl = buildGitHubAuthUrl()
                    Logger.d("DEV_RANK_AUTH", "OPENING GITHUB LOGIN")
                    Logger.d("DEV_RANK_AUTH", "AUTH URL = $authUrl")

                    uriHandler.openUri(authUrl)
                },
            )

            Spacer(Modifier.height(Spacing.lg))

            AnimatedVisibility(
                visible = state.error != null,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                state.error?.let { ErrorBanner(message = it) }
            }

            Spacer(Modifier.height(Spacing.xxl))

            Text(
                text = "By continuing, you agree to our Terms & Privacy Policy",
                style = MaterialTheme.typography.bodySmall.copy(color = TextSubtle),
                textAlign = TextAlign.Center,
            )
        }
    }
}

// ── Brand mark — reuses the app icon in the same glow-card treatment
//    used on the splash screen, just smaller ─────────────────────────
@Composable
private fun BrandMark() {
    val infiniteTransition = rememberInfiniteTransition(label = "login-brand")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "brandAlpha",
    )

    Box(
        modifier = Modifier
            .size(96.dp)
            .alpha(alpha)
            .clip(RoundedCornerShape(28.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF15103F),
                        Color(0xFF080D2B),
                        Color(0xFF06152D),
                    )
                )
            )
            .border(
                width = 1.5.dp,
                brush = Brush.linearGradient(
                    colors = listOf(AccentBlueLight, AccentBlue, AccentBlueDark)
                ),
                shape = RoundedCornerShape(28.dp),
            )
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = AccentBlue,
                spotColor = AccentBlueLight,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(Res.drawable.dev_rank_icon),
            contentDescription = "DevRank",
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .clip(RoundedCornerShape(18.dp)),
            contentScale = ContentScale.Crop,
        )
    }
}

// ── GitHub login button — monogram badge + label, loading state ────
@Composable
private fun GitHubLoginButton(
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(PillShape)
            .background(TextPrimary) // white pill, GitHub-button convention
            .border(BorderWidth.default, BorderMuted, PillShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !isLoading,
                onClick = onClick,
            )
            .padding(horizontal = Spacing.xl),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp,
                color = BgDefault,
            )
            Spacer(Modifier.width(Spacing.sm))
            Text(
                text = "Connecting…",
                color = BgDefault,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        } else {
            GitHubMonogram()
            Spacer(Modifier.width(Spacing.sm))
            Text(
                text = "Continue with GitHub",
                color = BgDefault,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

// ── Simple GH monogram badge (no external icon asset needed) ───────
@Composable
private fun GitHubMonogram() {
    Box(
        modifier = Modifier
            .size(22.dp)
            .clip(CircleShape)
            .background(BgDefault),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "GH",
            color = TextPrimary,
            fontSize = 9.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.02.sp,
        )
    }
}

// ── Error banner ─────────────────────────────────────────────────
@Composable
private fun ErrorBanner(message: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        modifier = Modifier
            .fillMaxWidth()
            .clip(DevRankShapes.medium)
            .background(AccentRedGhost)
            .border(BorderWidth.default, AccentRed.copy(alpha = 0.4f), DevRankShapes.medium)
            .padding(horizontal = Spacing.md, vertical = Spacing.sm),
    ) {
        Text(text = "⚠️", fontSize = 14.sp)
        Text(
            text = message,
            color = AccentRed,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}