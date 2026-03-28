package com.kundutechstudio.database.domain.repo

import com.kundutechstudio.database.cache_key.CacheKey
import kotlinx.serialization.KSerializer

interface CacheDataSourceRepo {

    suspend fun <T> save(
        key: CacheKey,
        data: T,
        serializer: KSerializer<T>
    )

    suspend fun <T> read(
        key: CacheKey,
        serializer: KSerializer<T>
    ): T?

    suspend fun clear(key: CacheKey)
}