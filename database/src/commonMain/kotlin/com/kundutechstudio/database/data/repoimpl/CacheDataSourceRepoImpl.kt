package com.kundutechstudio.database.data.repoimpl


import com.kundutechstudio.database.cache_key.CacheKey
import com.kundutechstudio.database.data.cache.CacheWrapper
import com.kundutechstudio.database.data.cache.FileCache
import com.kundutechstudio.database.domain.repo.CacheDataSourceRepo
import com.kunduthchstudio.utility.platform_utility.PlatformUtility
import kotlinx.serialization.json.Json
import kotlinx.serialization.KSerializer


class CacheDataSourceRepoImpl(
    private val fileCache: FileCache
) : CacheDataSourceRepo {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun <T> save(
        key: CacheKey,
        data: T,
        serializer: KSerializer<T>
    ) {
        val wrapper = CacheWrapper(
            data = data,
            timestamp = PlatformUtility.currentTimeSeconds()
        )

        val encoded = json.encodeToString(
            CacheWrapper.serializer(serializer),
            wrapper
        )

        fileCache.save(key.name, encoded)
    }

    override suspend fun <T> read(
        key: CacheKey,
        serializer: KSerializer<T>
    ): T? {
        val raw = fileCache.read(key.name) ?: return null

        return try {
            val wrapper = json.decodeFromString(
                CacheWrapper.serializer(serializer),
                raw
            )

            val currentTime = PlatformUtility.currentTimeSeconds()

            val isExpired = currentTime - wrapper.timestamp >= (3 * 60 * 60)

            if (isExpired) null else wrapper.data

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun clear(key: CacheKey) {
        fileCache.clear(key.name)
    }
}