package cb.pulse.network.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {

    fun getInstance(): HttpClient = HttpClient {

        /** VERY IMPORTANT
         *  Throws exceptions for 4xx / 5xx
         *  Enables proper error handling & token refresh
         */
        expectSuccess = true

        /**JSON serialization */
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    isLenient = true
                    encodeDefaults = true
                }
            )
        }

        /**  Base request configuration */
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "devapihrms.cloudbalance.in"
            }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.Accept, ContentType.Application.Json)
        }

        /**  Network timeouts */
        install(HttpTimeout) {
            socketTimeoutMillis = 15_000
            connectTimeoutMillis = 15_000
            requestTimeoutMillis = 15_000
        }


        /** Global error logging (optional but recommended) */
        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause, _ ->
                if (cause is ResponseException) {
                    // This logs raw server error body (VERY useful for debugging)
                    println("HTTP ERROR: ${cause.response.status}")
                }
            }
        }
    }
}
