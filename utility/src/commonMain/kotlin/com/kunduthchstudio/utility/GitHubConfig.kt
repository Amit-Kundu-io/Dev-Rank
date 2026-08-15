/**
 * GitHubConfig.kt
 *
 * Author      : Amit Kundu
 * Created On  : 15/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.kunduthchstudio.utility

object GitHubConfig {

    const val CLIENT_ID = "YOUR_GITHUB_CLIENT_ID"
    const val CLIENT_SECRET = "YOUR_GITHUB_CLIENT_secret"


    const val AUTHORIZE_URL = "https://github.com/login/oauth/authorize"
    const val TOKEN_URL = "https://github.com/login/oauth/access_token"
    const val REDIRECT_URI = "devrank://oauth/callback"
}