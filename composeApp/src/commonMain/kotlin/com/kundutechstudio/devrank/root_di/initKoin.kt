package com.kundutechstudio.devrank.root_di


import cb.pulse.network.di.getNetworkModule
import com.kundutechstudio.database.Di.databaseModules
import com.kundutechstudio.ranks.di.getRanksModules
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        printLogger(Level.DEBUG)
        koinApplication?.invoke(this)

        modules(
            getNetworkModule(),
            getRanksModules(),
            databaseModules(),
        )
    }
}