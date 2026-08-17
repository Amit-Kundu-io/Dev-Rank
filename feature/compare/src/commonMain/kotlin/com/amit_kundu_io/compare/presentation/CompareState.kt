/**
 * CompareState.kt
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

import androidx.compose.runtime.Immutable
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperComparison

@Immutable
data class CompareState(
    val usernameA: String = "",
    val usernameB: String = "",

    val isLoading: Boolean = false,

    val comparison: DeveloperComparison? = null,

    val error: String? = null,
)