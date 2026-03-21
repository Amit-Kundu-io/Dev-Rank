package com.kundutechstudio.ranks.di

import com.kundutechstudio.ranks.data.Apis.Apis
import com.kundutechstudio.ranks.data.repoImpl.RankRepoImpl
import com.kundutechstudio.ranks.domain.repo.RankRepo
import com.kundutechstudio.ranks.presentation.rank_screen.RankViewModel
import com.kundutechstudio.ranks.presentation.rank_screen.Repositories.RepositoriesViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun getRanksModules() = module {

    singleOf(::Apis)
    singleOf(::RankRepoImpl) { bind<RankRepo>() }

    viewModelOf(::RankViewModel)
    viewModelOf(::RepositoriesViewModel)
}