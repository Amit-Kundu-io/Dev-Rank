/**
 * RefreshTokenRepository.kt
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

package com.kundutechstudio.network.data.RefreshTokenRepository

interface RefreshTokenRepository  {

     suspend fun refreshToken(): Boolean

    /** Current access token, or null if not logged in. */
    suspend fun getAccessToken(): String?

    /** Clears stored tokens — call this on logout or when refresh fails. */
    suspend fun clearSession()
}
