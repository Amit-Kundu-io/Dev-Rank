package com.kundutechstudio.devrank

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kunduthchstudio.utility.Logger.Logger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        
        // Log initial intent
        intent?.data?.let {
            Logger.d("DEV_RANK_AUTH", "Step 0: MainActivity onCreate with data: $it")
        }

        setContent {
            App()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        
        // Log new intent (when app is already running)
        intent.data?.let {
            Logger.d("DEV_RANK_AUTH", "Step 0.5: MainActivity onNewIntent with data: $it")
        }
    }
}