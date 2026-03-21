package com.kundutechstudio

import android.app.Application
import com.kundutechstudio.devrank.root_di.initKoin
import org.koin.android.ext.koin.androidContext

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin { koinApp ->
            koinApp.androidContext(this)
        }
    }
}