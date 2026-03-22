package com.kundutechstudio.ranks.domain.use_case.get_top_starred_repo_use_case

import com.kundutechstudio.network.res.NetworkResult
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO
import com.kundutechstudio.ranks.domain.mapper.toRepoItem
import com.kundutechstudio.ranks.domain.repo.RankRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlin.collections.emptyList

class GetTopStarredRepoUseCase(
    private val repo: RankRepo
) {

        operator fun invoke(): Flow<NetworkResult<List<RepoItemDAO>>> = flow {

            emit(NetworkResult.Loading)

            try {
                val response = repo.getTopStarredRepos()

                val data: List<RepoItemDAO> = response.items?.mapIndexed { index, item ->
                    item.toRepoItem(rank = index + 1)
                } ?: emptyList()

                emit(NetworkResult.Success(data))

            } catch (e: Exception) {
                emit(
                    NetworkResult.Error(
                        message = e.message ?: "Something went wrong",
                        cause = e
                    )
                )
            }

        }.flowOn(Dispatchers.IO)
    }