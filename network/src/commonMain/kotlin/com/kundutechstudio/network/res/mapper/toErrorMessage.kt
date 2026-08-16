/**
 * toErrorMessage.kt
 *
 * Author      : Amit Kundu
 * Created On  : 14/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.kundutechstudio.network.res.mapper

import com.amit_kundu_io.corenetwork.util.ApiErrorResponse
import com.amit_kundu_io.corenetwork.util.NetworkException
import com.kundutechstudio.network.res.NetworkUtils
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.io.IOException

suspend fun Throwable.toErrorMessage(): String {

    return when (this) {

        is ClientRequestException -> {
            runCatching {
                response
                    .body<ApiErrorResponse>()
                    .error
                    .takeIf { it.isNotBlank() }
            }.getOrNull()
                ?: statusCodeFallbackMessage(response.status.value)
        }

        is RedirectResponseException ->
            "Unexpected server response. Please try again."

        is ServerResponseException ->
            NetworkException
                .ServerError(response.status.value)
                .message
                ?: "Server error. Please try again later."

        is HttpRequestTimeoutException,
        is ConnectTimeoutException,
        is SocketTimeoutException ->
            NetworkException.Timeout().message
                ?: "The request timed out."

        is UnresolvedAddressException ->
            "Unable to find the server. Please check your connection."

        is IOException -> {

            "Unable to connect to the server. Please try again."
        }

        is NetworkException -> message ?: "Network error. Please try again."

        else -> "Something went wrong. Please try again."
    }
}


private inline fun <reified T : Throwable> Throwable.findCause(): T? {

    var current: Throwable? = this

    while (current != null) {

        if (current is T) {
            return current
        }

        current = current.cause
    }

    return null
}

private fun statusCodeFallbackMessage(statusCode: Int): String {
    return when (statusCode) {
        400 -> "Invalid request."
        401 -> NetworkUtils.LOGIN_AGAIN_ERROR
        403 -> "You don't have permission to perform this action."
        404 -> "The requested resource was not found."
        405 -> "This operation is not supported."
        408 -> "The request timed out."
        409 -> "This request conflicts with the current state."
        422 -> "The submitted data is invalid."
        429 -> "Too many requests. Please try again later."
        500 -> "Server error. Please try again later."
        502 -> "The server is temporarily unavailable."
        503 -> "The service is temporarily unavailable."
        504 -> "The server took too long to respond."
        else -> "Request failed. Please try again."
    }
}