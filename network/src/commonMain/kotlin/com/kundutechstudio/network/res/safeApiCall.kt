/**
 * safeApiCall.kt
 *
 * Author      : Amit Kundu
 * Created On  : 16/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.kundutechstudio.network.res

import com.kundutechstudio.network.data.RefreshTokenRepository.RefreshTokenRepository
import com.kundutechstudio.network.res.mapper.toErrorMessage
import com.kunduthchstudio.utility.Logger.Logger
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.cancellation.CancellationException


/**
 * Executes an API request safely and exposes its lifecycle as:
 *
 * Loading -> Success
 * Loading -> Error
 *
 * Token refresh:
 *
 * Request
 *   ↓
 * 401
 *   ↓
 * refreshToken()
 *   ↓
 * retry once
 *
 * [refreshTokenRepo] is optional.
 *
 * If it is null, a 401 is returned as an authentication error
 * without attempting token refresh.
 *
 * [onSuccess] is intended for optional persistence/cache operations.
 *
 * [onError] is intended for logging/diagnostics and should NOT be
 * used for displaying errors directly to the user.
 *
 * [onEmit] controls how the successful response is emitted.
 * By default it emits NetworkResource.Success(response).
 */

fun <T> safeApiCall(
    apiCall: suspend () -> T,
    refreshTokenRepo: RefreshTokenRepository? = null,
    onSuccess: suspend (T) -> Unit = {},
    onError: (Throwable) -> Unit = {},
): Flow<NetworkResult<T>> = safeApiCall<T, T>(
    apiCall = apiCall,
    refreshTokenRepo = refreshTokenRepo,
    onSuccess = onSuccess,
    onError = onError,
    onEmit = { emit(NetworkResult.Success(it)) },
)


fun  <T, R> safeApiCall(
    apiCall: suspend () -> T,
    refreshTokenRepo: RefreshTokenRepository? = null,
    onSuccess: suspend (T) -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onEmit: suspend FlowCollector<NetworkResult<R>>.(T) -> Unit,
): Flow<NetworkResult<R>> = flow {

    /**Initial state.*/
    emit(NetworkResult.Loading)

    /**
     * Prevent infinite token-refresh loops.
     * One safeApiCall() execution can refresh the token only once.
     */
    var refreshAttempted = false

    while (true) {

        try {

            /**
             * Execute the API request
             * IMPORTANT: apiCall should obtain the latest access token here.
             */

            val response = apiCall()

            /**
             * A persistence failure does not make the API request
             * itself unsuccessful.
             */
            try {
                onSuccess(response)

            } catch (e: CancellationException) {

                /**Never convert coroutine cancellation into an error.*/
                throw e

            } catch (e: Exception) {

                /**
                 * Persistence/cache failed.
                 * Report it for logging/diagnostics but keep the
                 * successful API response.
                 */
                reportError(onError = onError, throwable = e)
            }

            /**
             * Emit the successful response.
             *
             * IMPORTANT: Do NOT emit NetworkResource.Success() again after this.
             * onEmit may customize the emitted result.
             */

            onEmit(response)
            return@flow

        } catch (e: CancellationException) {

            /**
             * Coroutine cancellation is NOT an API error.
             * Required for:
             * - ViewModel cancellation
             * - screen navigation
             * - WorkManager cancellation
             * - structured concurrency
             */

            throw e

        } catch (e: ClientRequestException) {

            /**
             * Ktor ClientRequestException represents HTTP 4xx errors
             * when expectSuccess = true.
             */
            val unauthorized = e.response.status.value == 401

            /***
             * Handle 401 only when:
             *
             * 1. Server returned 401
             * 2. Refresh repository exists
             * 3. Refresh hasn't already been attempted
             */
            if (unauthorized && refreshTokenRepo != null && !refreshAttempted) {

                /***
                 * Mark BEFORE refreshing.
                 * This guarantees that even if the retry also returns
                 * 401, another refresh will not be attempted.
                 */
                refreshAttempted = true

                val refreshed = try {

                    refreshTokenRepo.refreshToken()

                } catch (e: CancellationException) {

                    /** Never swallow cancellation. */
                    throw e

                } catch (e: Exception) {

                    /***
                     * Refresh failed
                     * Report the real exception for diagnostics.
                     */

                    reportError(onError = onError, throwable = e)

                    false
                }

                /** Refresh succeeded.
                 * Retry the original API request once.
                 */

                if (refreshed) { continue }

                /***  Refresh failed.
                 * Do not expose the internal refresh exception
                 * to the UI.
                 */

                emit(NetworkResult.Error(""))
                return@flow

            }

            /***
             * Either:
             * - this isn't a 401
             * - refresh repository isn't available
             * - refresh was already attempted
             *
             * Therefore return the normal mapped error.
             */

            reportError(onError = onError, throwable = e)

            emit(NetworkResult.Error(e.toErrorMessage()))

            return@flow

        } catch (e: CancellationException) {

            /****
             * Defensive cancellation handling.
             *
             * This catch is technically covered by the earlier
             * CancellationException catch and should normally never
             * be reached.
             */

            throw e

        } catch (e: Exception) {

            /**** Final safety net for unexpected exceptions.*/

            reportError(onError = onError, throwable = e)

            emit(NetworkResult.Error(e.toErrorMessage()))

            return@flow
        }
    }
}


/**
 * Safely reports an exception to the diagnostic callback.
 * Logging must never break the API flow.
 *
 * Example:
 * }
 */
private fun reportError(
    onError: (Throwable) -> Unit,
    throwable: Throwable
) {
    // Notify the caller for logging or additional error handling.
    // runCatching prevents the error callback itself from crashing the flow.
    runCatching {
        onError(throwable)
    }

    // Log the original error for debugging.
    // Logger only prints logs in debug builds, not in production.

    Logger.e(
        tag = "SafeApiCall",
        message = throwable.message ?: "Unknown error",
        throwable = throwable
    )
}