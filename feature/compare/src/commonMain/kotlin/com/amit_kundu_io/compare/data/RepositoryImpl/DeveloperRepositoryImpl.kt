/**
 * DeveloperRepositoryImpl.kt
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

package com.amit_kundu_io.compare.data.RepositoryImpl

import com.amit_kundu_io.compare.data.apis.DeveloperApi
import com.amit_kundu_io.compare.data.models.GitHubDeveloperResponse.GitHubDeveloperResponse
import com.amit_kundu_io.compare.domain.Repository.DeveloperRepository
import com.kunduthchstudio.utility.date_time_utility.DateTimeUtility


class DeveloperRepositoryImpl(
    private val api: DeveloperApi,
) : DeveloperRepository {

    override suspend fun getDeveloperStats(
        username: String,
        token: String,
    ): GitHubDeveloperResponse {
        val (from, to) = DateTimeUtility.currentYearDateRange()

        return api.getDeveloper(
            username = username,
            token = token,
            from = from,
            to = to
        )
    }
}