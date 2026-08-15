package com.kundutechstudio.auth.data.repo_impl

import com.kunduthchstudio.utility.GitHubConfig.CLIENT_ID
import com.kunduthchstudio.utility.GitHubConfig.CLIENT_SECRET
import com.kunduthchstudio.utility.GitHubConfig.REDIRECT_URI
import com.kunduthchstudio.utility.Logger.Logger
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class AuthRepository(
    private val httpClient: HttpClient
) {
    suspend fun exchangeCodeForToken(code: String): String {
        val response: String = httpClient.post("https://github.com/login/oauth/access_token") {
            headers { append("Accept", "application/json") }
            parameter("client_id", CLIENT_ID)
            parameter("client_secret", CLIENT_SECRET)
            parameter("code", code)
            parameter("redirect_uri", REDIRECT_URI)
        }.bodyAsText()

        Logger.d("DEV_RANK_AUTH", "Token Response: $response")

        return try {
            val json = Json.parseToJsonElement(response).jsonObject
            json["access_token"]?.jsonPrimitive?.content ?: ""
        } catch (e: Exception) {
            Logger.d("DEV_RANK_AUTH", "Parsing Error: ${e.message}")
            ""
        }
    }

    suspend fun getUserData(token: String): String {
        return try {
            val response: String = httpClient.get("https://api.github.com/user") {
                headers {
                    append("Authorization", "Bearer $token")
                    append("Accept", "application/vnd.github+json")
                }
            }.bodyAsText()
            Logger.d("DEV_RANK_AUTH", "User Data: $response")
            response
        } catch (e: Exception) {
            Logger.d("DEV_RANK_AUTH", "User Data Error: ${e.message}")
            ""
        }
    }
}