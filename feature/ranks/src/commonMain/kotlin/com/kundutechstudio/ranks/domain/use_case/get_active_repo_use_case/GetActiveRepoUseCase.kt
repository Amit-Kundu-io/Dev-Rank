package com.kundutechstudio.ranks.domain.use_case.get_active_repo_use_case

import com.kundutechstudio.database.cache_key.CacheKey
import com.kundutechstudio.database.domain.repo.CacheDataSourceRepo
import com.kundutechstudio.network.res.NetworkResult
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO
import com.kundutechstudio.ranks.domain.mapper.toRepoItem
import com.kundutechstudio.ranks.domain.repo.RankRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.builtins.ListSerializer


class GetActiveRepoUseCase(
    private val repo: RankRepo,
    private val cache: CacheDataSourceRepo
) {

    private val serializer = ListSerializer(RepoItemDAO.serializer())

    operator fun invoke(
        query: String = "pushed:>2026-03-20 stars:>100"
    ): Flow<NetworkResult<List<RepoItemDAO>>> = flow {

        emit(NetworkResult.Loading)

        // Try cache first
        val cached = cache.read(CacheKey.ACTIVE_REPO, serializer)

        if (!cached.isNullOrEmpty()) {
            emit(NetworkResult.Success(cached))
            return@flow
        }

        //Fetch from API
        val response = repo.getRepositories(query)

        val data = response.items
            ?.mapIndexed { index, item ->
                item.toRepoItem(rank = index + 1)
            }
            .orEmpty()

        // Save cache
        cache.save(CacheKey.ACTIVE_REPO, data, serializer)

        emit(NetworkResult.Success(data))

    }.catch { e ->
        emit(
            NetworkResult.Error(
                message = e.message ?: "Something went wrong",
                cause = e
            )
        )
    }.flowOn(Dispatchers.IO)
}