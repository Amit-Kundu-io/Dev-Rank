package com.kundutechstudio.profile.di

import com.kundutechstudio.profile.data.Models.repo_impl.PrefileRepoImpl
import com.kundutechstudio.profile.data.apis.Apis
import com.kundutechstudio.profile.domain.repo.PrefileRepo
import com.kundutechstudio.profile.domain.use_case.get_contribution_graph_use_case.GetContributionGraphUseCase
import com.kundutechstudio.profile.presentation.PrefileScreen.PrefileViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun profileModule() = module {
    singleOf(::Apis)
    singleOf(::PrefileRepoImpl) { bind<PrefileRepo>() }

    factoryOf(::GetContributionGraphUseCase)

    viewModelOf(::PrefileViewModel)
}