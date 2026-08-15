package com.kundutechstudio.auth.data


actual fun handleOAuthCallback(
    url: String
) {
    OAuthCallbackReceiver.onCallback(url)
}