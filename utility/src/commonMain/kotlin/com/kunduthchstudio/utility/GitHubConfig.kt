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
    const val CLIENT_ID = "Ov23li3RwvcGyHOV0Pan"
    const val CLIENT_SECRET = "fc6b569cb13d8ae30371eb6b6c0c67ea504f6fd6"


    const val AUTHORIZE_URL = "https://github.com/login/oauth/authorize"
    const val TOKEN_URL = "https://github.com/login/oauth/access_token"
    const val REDIRECT_URI = "devrank://oauth/callback"
}

