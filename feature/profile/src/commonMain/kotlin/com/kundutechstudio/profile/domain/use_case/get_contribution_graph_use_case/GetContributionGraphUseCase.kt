package com.kundutechstudio.profile.domain.use_case.get_contribution_graph_use_case

import com.kundutechstudio.network.res.NetworkResult
import com.kundutechstudio.profile.domain.repo.PrefileRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class GetContributionGraphUseCase(
    private val repo: PrefileRepo

) {

    operator fun invoke(
        username: String,
        token: String? = null
    ): Flow<NetworkResult<List<List<Int>>>> = flow {

        emit(NetworkResult.Loading)

        try {
            val response = repo.getContributionResponse(username, token)

            val weeks = response.data.user
                .contributionsCollection
                .contributionCalendar
                .weeks

            val grid = weeks.map { week ->
                week.contributionDays.map { day ->
                    getLevel(day.contributionCount)
                }
            }

            emit(NetworkResult.Success(grid))

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Error"))
        }

    }.flowOn(Dispatchers.IO)

    fun getLevel(count: Int): Int {
        return when {
            count == 0 -> 0
            count <= 3 -> 1
            count <= 6 -> 2
            count <= 10 -> 3
            else -> 4
        }
    }
}

