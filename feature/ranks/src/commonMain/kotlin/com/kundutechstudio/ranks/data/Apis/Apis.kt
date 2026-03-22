package com.kundutechstudio.ranks.data.Apis

import com.kundutechstudio.ranks.data.models.RepoResponse.RepoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter

class Apis(
    private val client: HttpClient
) {
    suspend fun getTopStarredRepos():RepoResponse {
        val response = client.get("https://api.github.com/search/repositories") {
            parameter("q", "stars:>1")
            parameter("sort", "stars")
            parameter("order", "desc")
            parameter("per_page", 10)

//            // Optional (recommended)
//            headers {
//                append("Accept", "application/vnd.github+json")
//            }
        }

        return response.body()
    }

    suspend fun getTodayTrendingRepos(
        date: String, // e.g. "2026-03-22"
        authorization: String? = null
    ): RepoResponse {

            return client.get("https://api.github.com/search/repositories") {

                header("Accept", "application/vnd.github+json")

                authorization?.let {
                    header("Authorization", "Bearer $it")
                }

                // 🔥 Trending Now logic
                parameter("q", "created:>$date stars:>50")
                parameter("sort", "stars")
                parameter("order", "desc")
                parameter("per_page", 50)

            }.body()
    }

}