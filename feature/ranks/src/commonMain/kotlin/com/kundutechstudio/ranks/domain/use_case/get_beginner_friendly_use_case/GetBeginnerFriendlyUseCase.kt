package com.kundutechstudio.ranks.domain.use_case.get_beginner_friendly_use_case

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

class GetBeginnerFriendlyUseCase(
    private val repo: RankRepo,
    private val cache: CacheDataSourceRepo
) {

    private val serializer = ListSerializer(RepoItemDAO.serializer())

    operator fun invoke(
        query: String = "good-first-issues:>5 stars:>50"
    ): Flow<NetworkResult<List<RepoItemDAO>>> = flow {

        emit(NetworkResult.Loading)

        //   Try cache first
        val cached = cache.read(CacheKey.BEGINNER_FRIENDLY, serializer)

        if (!cached.isNullOrEmpty()) {
            emit(NetworkResult.Success(cached))
            return@flow
        }

        // API call
        val response = repo.getRepositories(query)

        val data = response.items
            ?.mapIndexed { index, item ->
                item.toRepoItem(rank = index + 1)
            }
            .orEmpty()

        //  Save cache
        cache.save(CacheKey.BEGINNER_FRIENDLY, data, serializer)

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