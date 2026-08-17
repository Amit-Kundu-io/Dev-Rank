/**
 * ApiErrorResponse.kt
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


import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val success: Boolean,
    val statusCode: Int,
    val timestamp: String,
    val path: String,
    val error: String
)