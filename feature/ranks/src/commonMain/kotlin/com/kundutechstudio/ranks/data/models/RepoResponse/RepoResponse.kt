package com.kundutechstudio.ranks.data.models.RepoResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RepoResponse(
    val incomplete_results: Boolean,
 //   val items: List<Item>? = null,
    val total_count: Int
)