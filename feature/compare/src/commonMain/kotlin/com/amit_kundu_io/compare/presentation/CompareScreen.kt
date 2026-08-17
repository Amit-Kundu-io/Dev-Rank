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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amit_kundu_io.compare.presentation.componeants.DeveloperHeaderComparison
import com.amit_kundu_io.compare.presentation.componeants.MetricComparisonCard
import com.amit_kundu_io.compare.presentation.componeants.WinnerCard
import com.kundutechstudio.theme.Components.Comparecomponents.VsBadge
import com.kundutechstudio.theme.Components.TextField.DevRankTextField
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kunduthchstudio.utility.GlobalUtility.sanitizeGithubId
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
    val keyboardController = LocalSoftwareKeyboardController.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 150.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item(
            key = "Amit_Kundu_Key_010"
        ) {

            Text(
                text = "Developer Compare",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),

            )
        }

        item(
            key = "developer-a-input"
        ) {

            DevRankTextField(
                value = state.usernameA,
                onValueChange = {
                    onAction(
                        CompareAction.UsernameAChanged(sanitizeGithubId(it))
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



        item(
            key = "developer-b-input"
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                VsBadge()
            }

            DevRankTextField(
                value = state.usernameB,
                onValueChange = {
                    onAction(
                        CompareAction.UsernameBChanged(sanitizeGithubId(it))
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

        item(
            key = "compare-button"
        ) {

            Button(
                onClick = {
                    keyboardController?.hide()
                    onAction(
                        CompareAction.Compare
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Compare Developers")
            }
        }



        item(
            key = "loading-indicator"
        ) {
            if (state.isLoading) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }



        item(
            key = "cvhncbcyj"
        ) {
            state.comparison?.let { comparison ->
                WinnerCard(
                    comparison = comparison
                )

                Spacer(modifier = Modifier.height(16.dp))

                DeveloperHeaderComparison(comparison = comparison)
            }
        }


        items(
            items = state.comparison?.metricResults ?: emptyList(),
            key = { metric -> metric.metric.name }
        ) { metric ->

            MetricComparisonCard(
                metric = metric,
                developerA = state.comparison?.developerA?.username ?: "",
                developerB = state.comparison?.developerB?.username ?: "",
            )
        }




        item(
            key = "error-message"
        ) {
            state.error?.let { error ->
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