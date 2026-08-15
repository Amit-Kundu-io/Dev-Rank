package com.kundutechstudio.devrank.oauth

import com.kundutechstudio.auth.data.oauth.OAuthCallbackReceiver

object OAuthCallbackBridge {

    fun handle(url: String) {
        OAuthCallbackReceiver.onCallback(url)
    }
}