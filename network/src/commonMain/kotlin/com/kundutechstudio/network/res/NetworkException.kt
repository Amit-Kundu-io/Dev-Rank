/**
 * NetworkException.kt
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

package com.amit_kundu_io.corenetwork.util

/**
 * Base type for all network-layer failures thrown/emitted within
 * corenetwork. Carries a human-readable [message] and, where available,
 * the original [cause] so nothing gets lost when it's converted into a
 * [com.amit_kundu_io.corenetwork.NetworkResource.Error].
 */
sealed class NetworkException(message: String) : Exception(message) {

    /**
     * No network connectivity available (DNS failure, no connection,
     * airplane mode, etc). Maps from [io.ktor.util.network.UnresolvedAddressException]
     * and generic [kotlinx.io.IOException].
     */
    class NoNetwork(): NetworkException(
        message = "No internet connection. Please check your network and try again.",

    )

    /**
     * Request exceeded its time limit — connect timeout, socket timeout,
     * or overall request timeout.
     */
    class Timeout() : NetworkException(message = "The request timed out. Please try again.",)

    /**
     * 5xx response from the server.
     *
     * @param statusCode the HTTP status code returned (500, 502, 503, ...)
     */
    class ServerError(
        val statusCode: Int,
    ) : NetworkException(
        message = "Server error ($statusCode). Please try again later.",

    )

    /**
     * 4xx response from the server that isn't specifically handled
     * elsewhere (e.g. 400, 403, 404, 409, 422).
     *
     * @param statusCode the HTTP status code returned
     * @param serverMessage optional message parsed from the error response body
     */
    class ClientError(
        val statusCode: Int,
        val serverMessage: String? = null,
    ) : NetworkException(
        message = serverMessage ?: "Request failed ($statusCode).",

    )

    /**
     * 401 Unauthorized where no refresh was attempted, or a refresh was
     * attempted and failed. The caller should route the user to login.
     */
    class Unauthorized(

    ) : NetworkException(
        message = "Your session has expired. Please log in again.",

    )

    /**
     * Response body could not be parsed / deserialized into the expected type.
     */
    class SerializationError(
    ) : NetworkException(
        message = "Failed to process the server response.",
    )

    /**
     * Fallback for anything that doesn't map to a more specific case above.
     */
    class Unknown(
        originalMessage: String? = null,
    ) : NetworkException(
        message = originalMessage ?: "Something went wrong. Please try again.",
    )
}