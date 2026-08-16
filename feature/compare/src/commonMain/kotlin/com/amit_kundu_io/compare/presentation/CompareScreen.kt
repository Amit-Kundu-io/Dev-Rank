/**
 * CompareScreen.kt
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

package com.amit_kundu_io.compare.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amit_kundu_io.compare.presentation.componeants.DeveloperHeaderComparison
import com.amit_kundu_io.compare.presentation.componeants.MetricComparisonCard
import com.amit_kundu_io.compare.presentation.componeants.WinnerCard
import com.kundutechstudio.theme.Components.TextField.DevRankTextField
import com.kundutechstudio.theme.Components.TextField.TextFieldVariant
import com.kundutechstudio.theme.ui.DevRankTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CompareRootScreen(
    viewModel: CompareViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CompareScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CompareScreen(
    state: CompareState,
    onAction: (CompareAction) -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(
            vertical = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "Developer Compare",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        item {

            DevRankTextField(
                value = state.usernameA,
                onValueChange = {
                    onAction(
                        CompareAction.UsernameAChanged(it)
                    )
                },
                label = "Developer 1",
                placeholder = "e.g. torvalds",
                singleLine = true,
//                variant = when {
//                    errorMessage != null -> TextFieldVariant.Error
//                    isValid -> TextFieldVariant.Success
//                    else -> TextFieldVariant.Default
//                },
            )
        }

        item {

            DevRankTextField(
                value = state.usernameB,
                onValueChange = {
                    onAction(
                        CompareAction.UsernameBChanged(it)
                    )
                },
                label = "Developer 2",
                placeholder = "e.g. torvalds",
                singleLine = true,
//                variant = when {
//                    errorMessage != null -> TextFieldVariant.Error
//                    isValid -> TextFieldVariant.Success
//                    else -> TextFieldVariant.Default
//                },
            )
        }

        item {

            Button(
                onClick = {
                    onAction(
                        CompareAction.Compare
                    )
                },
                modifier =
                    Modifier.fillMaxWidth()
            ) {
                Text("Compare Developers")
            }
        }

        if (state.isLoading) {

            item {
                CircularProgressIndicator()
            }
        }

        state.comparison?.let { comparison ->

            item {
                WinnerCard(
                    comparison = comparison
                )
            }

            item {
                DeveloperHeaderComparison(comparison = comparison)
            }

            items(items = comparison.metricResults) { metric ->

                MetricComparisonCard(metric = metric,
                    developerA = comparison.developerA.username,
                    developerB = comparison.developerB.username,
                )
            }
        }

        state.error?.let { error ->

            item {
                Text(
                    text = error,
                    color =
                        MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DevRankTheme {
        CompareScreen(
            state = CompareState(),
            onAction = {}
        )
    }
}