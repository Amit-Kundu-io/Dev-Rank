package com.kundutechstudio.database.Di

import com.kundutechstudio.database.data.cache.FileCache
import com.kundutechstudio.database.data.repoimpl.CacheDataSourceRepoImpl
import com.kundutechstudio.database.datastore.DRDataStore
import com.kundutechstudio.database.datastore.createDataStore
import com.kundutechstudio.database.domain.repo.CacheDataSourceRepo
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun databaseModules() = module {
    singleOf(::FileCache)
    singleOf(::CacheDataSourceRepoImpl){bind<CacheDataSourceRepo>()}

    single { createDataStore(get()) }
    singleOf(::DRDataStore)
}