package com.kundutechstudio.devrank

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kundutechstudio.devrank.oauth.OAuthCallbackBridge
import com.kunduthchstudio.utility.Logger.Logger

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        Logger.d("DEV_RANK_AUTH", "MAIN ACTIVITY CREATED")

        Logger.d("DEV_RANK_AUTH", "INITIAL URI = ${intent?.data}")

        setContent {
            App()
        }

        /*
         * Handles the case where the app
         * was completely closed and GitHub
         * opened it using the callback URL.
         */
        intent?.data?.toString()?.let {

            Logger.d("DEV_RANK_AUTH", "INITIAL OAUTH CALLBACK")

            OAuthCallbackBridge.handle(it)
        }
    }

    override fun onNewIntent(intent: Intent) {

        super.onNewIntent(intent)

        setIntent(intent)

        Logger.d("DEV_RANK_AUTH", "MAIN ACTIVITY onNewIntent")

        Logger.d("DEV_RANK_AUTH", "CALLBACK URI = ${intent.data}")

        intent.data?.toString()?.let {
            OAuthCallbackBridge.handle(it)
        }
    }
}