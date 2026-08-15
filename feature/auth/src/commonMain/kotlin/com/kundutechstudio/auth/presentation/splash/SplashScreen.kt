package com.kundutechstudio.auth.presentation.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kundutechstudio.theme.ui.DevRankTheme
import common.resources.Res
import common.resources.dev_rank_icon
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SplashRootScreen(
    viewModel: SplashViewModel = koinViewModel(),
    onLoginScreen: () -> Unit,
    onDashboardScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {

        delay(1_000)

        onDashboardScreen.invoke()
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
    val infiniteTransition = rememberInfiniteTransition(
        label = "splash"
    )

    // Subtle scale animation
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoScale"
    )

    // Subtle alpha animation
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.75f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoAlpha"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFF050817)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        SplashLogo(
            scale = scale,
            alpha = alpha
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "DevRank",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Track. Code. Rank.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF8B93A7)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp,
            color = Color(0xFF3B82F6)
        )
    }
}


@Composable
private fun SplashLogo(
    scale: Float,
    alpha: Float
) {
    Box(
        modifier = Modifier
            .size(220.dp)
            .scale(scale)
            .alpha(alpha)
            .clip(RoundedCornerShape(48.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF15103F),
                        Color(0xFF080D2B),
                        Color(0xFF06152D)
                    )
                )
            )
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFB832FF),
                        Color(0xFF315CFF),
                        Color(0xFF00D9FF)
                    )
                ),
                shape = RoundedCornerShape(48.dp)
            )
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(48.dp),
                ambientColor = Color(0xFF6C2BFF),
                spotColor = Color(0xFF009DFF)
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(
                Res.drawable.dev_rank_icon
            ),
            contentDescription = "DevRank",
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
                .clip(RoundedCornerShape(38.dp)),
            contentScale = ContentScale.Crop
        )
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