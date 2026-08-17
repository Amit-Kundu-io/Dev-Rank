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

    //this is key only Demo purpose and any time delete
    const val CLIENT_ID = "Ov23lid9UeE58cpQjbh4"
    const val CLIENT_SECRET = "aae655ff40dcd8a3e589d15398620014e0590f66"


    const val AUTHORIZE_URL = "https://github.com/login/oauth/authorize"
    const val TOKEN_URL = "https://github.com/login/oauth/access_token"
    const val REDIRECT_URI = "devrank://oauth/callback"
}

