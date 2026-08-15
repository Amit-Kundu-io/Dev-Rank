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

/**
 * Run this once in the terminal:
 *
 * git update-index --assume-unchanged "utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt"
 *
 * Why:
 * This tells Git to ignore local changes to this tracked configuration file,
 * so local GitHub credentials/configuration changes are not included
 * when you commit and push changes.
 *
 * Note:
 * This does NOT remove the file from Git or make it secure.
 * Do not store production secrets in this file.
 */

object GitHubConfig {

    const val CLIENT_ID = "YOUR_GITHUB_CLIENT_ID"
    const val CLIENT_SECRET = "YOUR_GITHUB_CLIENT_secret"


    const val AUTHORIZE_URL = "https://github.com/login/oauth/authorize"
    const val TOKEN_URL = "https://github.com/login/oauth/access_token"
    const val REDIRECT_URI = "devrank://oauth/callback"
}
