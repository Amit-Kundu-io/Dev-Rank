package com.kundutechstudio.devrank.root_di


import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        printLogger(Level.DEBUG)
        koinApplication?.invoke(this)

        modules(

        )
    }
}