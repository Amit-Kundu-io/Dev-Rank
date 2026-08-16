/**
 * DeveloperRepository.kt
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

package com.amit_kundu_io.compare.domain.Repository

import com.amit_kundu_io.compare.data.models.GitHubDeveloperResponse.GitHubDeveloperResponse
import com.amit_kundu_io.compare.domain.models.DeveloperStats
import com.kundutechstudio.network.res.NetworkResult


interface DeveloperRepository {

    suspend fun getDeveloperStats(
        username: String,
        token: String,
    ): GitHubDeveloperResponse
}