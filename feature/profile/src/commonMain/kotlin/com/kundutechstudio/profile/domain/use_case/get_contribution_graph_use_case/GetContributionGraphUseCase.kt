package com.kundutechstudio.profile.domain.use_case.get_contribution_graph_use_case

import com.kundutechstudio.database.datastore.DRDataStore
import com.kundutechstudio.network.res.NetworkResult
import com.kundutechstudio.profile.data.Models.ContributionResponse.GraphQLResponse
import com.kundutechstudio.profile.data.apis.Apis
import com.kundutechstudio.profile.domain.repo.PrefileRepo
import com.kundutechstudio.profile.presentation.PrefileScreen.toHeatmap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class GetContributionGraphUseCase(
    private val repo: PrefileRepo,
    private val sessionManager: DRDataStore
) {

    suspend operator fun invoke(
        username: String? = null,
        token: String? = null,
        from: String,
        to: String
    ): Flow<NetworkResult<GraphQLResponse>> = flow {

        emit(NetworkResult.Loading)

        val token = sessionManager.token.firstOrNull()

        if (token.isNullOrEmpty()) {
            emit(NetworkResult.Error("GitHub login required"))
            return@flow
        }

        try {
            val response = repo.getProfile(
                token = token,
                from =from,
                to = to
            )

            emit(NetworkResult.Success(response))

        } catch (e: Exception) {

            emit(NetworkResult.Error(e.message ?: "Failed to load contributions"))
        }
    }
}