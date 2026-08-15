/**
 * LockScreen.kt
 *
 * Author      : Amit Kundu
 * Created On  : 16/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.kundutechstudio.profile.presentation.PrefileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kundutechstudio.theme.Components.Badgecomponents.TrendingType
import com.kundutechstudio.theme.Components.Cardcomponents.RepoVerticalCard.RepoVerticalCard
import com.kundutechstudio.theme.Components.Cardcomponents.RepoVerticalCard.RepoVerticalCardSkeleton
import com.kundutechstudio.theme.Components.Leaderboardcomponents.SectionHeader
import com.kundutechstudio.theme.Components.Profilecomponents.YourRankCard
import com.kundutechstudio.theme.Components.Statcomponents.ContributionHeatmap
import com.kundutechstudio.theme.ui.AccentBlueGhost
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.Spacing
import org.jetbrains.compose.resources.painterResource

@Composable
fun LockScreen(
    onLoginClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Your actual screen
        PrefileScreen()

        // Dark background overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.80f)
                )
        )

        // Center lock card
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = BgOverlay
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        )
        {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 32.dp,
                        vertical = 28.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Locked",
                    modifier = Modifier.size(42.dp),
                    tint = Color.Gray
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Login Required",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Login to access this profile",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onLoginClick,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Login,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Login")
                }
            }
        }
    }
}

private fun fakeDataFun() = List(26) { w ->
    List(7) { d -> if (d < 8) listOf(0, 1, 2, 3, 8).random() else 0 }
}


@Composable
private fun PrefileScreen() {
    val listState = rememberLazyListState()

    val fakeData = remember {
        fakeDataFun()
    }

    Column {
        YourRankCard(
            initials = "AK",
            username = "Amit Kundu",
            imageUrl = "",
            bio = "Android Developer | Kotlin | Jetpack Compose",
            handle = "Amit_Kundu_io",
            followers = "6",
            repos =70.toString(),
            stars = 100.toString(),
        )

        LazyColumn(
            state = listState,
            modifier = Modifier.padding(Spacing.lg),
            contentPadding = PaddingValues(bottom = Spacing.huge)
        ) {

            item(
                key = "hader-contributions"
            ) {

                ContributionHeatmap(
                    levels = fakeData,
                    modifier = Modifier.padding(Spacing.lg),
                )
            }

            // Trending
            item(
                key = "hader-trending"
            ) {
                Column(
                ) {
                    SectionHeader(
                        title = "🔥 Top Repo",
                        badgeLabel = "CREATE",
                        badgeColor = AccentBlueLight,
                        badgeBg = AccentBlueGhost,
                    )
                }

            }

                item {
                    RepoVerticalCard(
                        name = "Dev-Rank",
                        description = "A developer ranking platform built with Kotlin Multiplatform and GitHub integration.",
                        stars = "128",
                        language = "Kotlin",
                        trendingLabel = "Trending",
                        trendingType = TrendingType.PUBLIC,
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )

                    RepoVerticalCard(
                        name = "Dev-Rank",
                        description = "A developer ranking platform built with Kotlin Multiplatform and GitHub integration.",
                        stars = "128",
                        language = "Kotlin",
                        trendingLabel = "Trending",
                        trendingType = TrendingType.PUBLIC,
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )

                    RepoVerticalCard(
                        name = "Dev-Rank",
                        description = "A developer ranking platform built with Kotlin Multiplatform and GitHub integration.",
                        stars = "128",
                        language = "Kotlin",
                        trendingLabel = "Trending",
                        trendingType = TrendingType.PUBLIC,
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                }

        }

    }
}