package com.kundutechstudio.ranks.di

import com.kundutechstudio.ranks.data.Apis.Apis
import com.kundutechstudio.ranks.data.repoImpl.RankRepoImpl
import com.kundutechstudio.ranks.domain.repo.RankRepo
import com.kundutechstudio.ranks.domain.use_case.get_active_repo_use_case.GetActiveRepoUseCase
import com.kundutechstudio.ranks.domain.use_case.get_beginner_friendly_use_case.GetBeginnerFriendlyUseCase
import com.kundutechstudio.ranks.domain.use_case.get_largest_repos_use_case.GetLargestReposUseCase
import com.kundutechstudio.ranks.domain.use_case.get_top_Treanding_repo_use_case.GetTopTrendingRepoUseCase
import com.kundutechstudio.ranks.domain.use_case.get_top_starred_repo_use_case.GetTopStarredRepoUseCase
import com.kundutechstudio.ranks.presentation.rank_screen.RankViewModel
import com.kundutechstudio.ranks.presentation.rank_screen.Repositories.RepositoriesViewModel
import com.kundutechstudio.ranks.presentation.rank_screen.top_starred_repo_list.TopStarredRepoListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun getRanksModules() = module {

    singleOf(::Apis)
    singleOf(::RankRepoImpl) { bind<RankRepo>() }

    factoryOf(::GetTopStarredRepoUseCase)
    factoryOf(::GetTopTrendingRepoUseCase)
    factoryOf(::GetLargestReposUseCase)
    factoryOf(::GetBeginnerFriendlyUseCase)
    factoryOf(::GetActiveRepoUseCase)

    viewModelOf(::RankViewModel)
    viewModelOf(::RepositoriesViewModel)
    viewModelOf(::TopStarredRepoListViewModel)
}