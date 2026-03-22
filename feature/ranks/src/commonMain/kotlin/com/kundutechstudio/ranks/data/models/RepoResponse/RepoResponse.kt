package com.kundutechstudio.ranks.data.models.RepoResponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RepoResponse(

    @SerialName("incomplete_results") val incompleteResults: Boolean? = null,
    @SerialName("items") val items: List<TopStarredRepoItem>? = null,
    @SerialName("total_count") val totalCount: Int? = null
)