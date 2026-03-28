package com.kundutechstudio.database.data.cache

import kotlinx.serialization.Serializable

@Serializable
data class CacheWrapper<T>(
    val data: T,
    val timestamp: Long
)