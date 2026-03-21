package com.kundutechstudio.ranks.data.models.RepoResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class License(

    @SerialName("key") val key: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("node_id") val nodeId: String? = null,
    @SerialName("spdx_id") val spdxId: String? = null,
    @SerialName("url") val url: String? = null
)