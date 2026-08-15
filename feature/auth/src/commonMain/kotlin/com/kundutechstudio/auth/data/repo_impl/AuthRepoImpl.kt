package com.kundutechstudio.auth.data.repo_impl

import com.kunduthchstudio.utility.GitHubConfig
import com.kunduthchstudio.utility.Logger.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import io.ktor.http.headers
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
class AuthRepository(
    private val httpClient: HttpClient
) {

    suspend fun exchangeCodeForToken(code: String): Result<String> {

        return runCatching {

            Logger.d("DEV_RANK_AUTH", "TOKEN API: starting")

            val response = httpClient.post(GitHubConfig.TOKEN_URL) {
                    header("Accept", "application/json")
                    parameter("client_id", GitHubConfig.CLIENT_ID)
                    parameter("client_secret", GitHubConfig.CLIENT_SECRET)
                    parameter("code", code)
                    parameter("redirect_uri", GitHubConfig.REDIRECT_URI)
                }

            val body = response.bodyAsText()

            Logger.d("DEV_RANK_AUTH", "TOKEN API: response received")

            Logger.d("DEV_RANK_AUTH", "TOKEN API: HTTP ${response.status.value}")

            val json = Json.parseToJsonElement(body).jsonObject

            val token = json["access_token"]?.jsonPrimitive?.content

            if (token.isNullOrEmpty()) {
                val error = json["error"]?.jsonPrimitive?.content
                error("GitHub token missing. Error=$error")
            }

            Logger.d("DEV_RANK_AUTH", "TOKEN API: access token received")

            /*
             * Don't log the actual token.
             */

            Logger.d("DEV_RANK_AUTH", "TOKEN ==== : $token")


            token
        }
    }
}