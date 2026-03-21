package cb.pulse.network.di
import cb.pulse.network.client.KtorClient


import org.koin.dsl.module

fun getNetworkModule () = module {

    single { KtorClient.getInstance() }

}