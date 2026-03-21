package com.kundutechstudio.ranks.presentation.di

import com.kundutechstudio.ranks.presentation.rank_screen.RankViewModel
import com.kundutechstudio.ranks.presentation.rank_screen.Repositories.RepositoriesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun ranksModules() = module {

    viewModelOf(::RankViewModel)
    viewModelOf(::RepositoriesViewModel)
}