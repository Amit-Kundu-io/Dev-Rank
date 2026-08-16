/**
 * DeveloperModule.kt
 *
 * Author      : Amit Kundu
 * Created On  : 16/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.amit_kundu_io.compare.di

import com.amit_kundu_io.compare.data.RepositoryImpl.DeveloperRepositoryImpl
import com.amit_kundu_io.compare.data.apis.DeveloperApi
import com.amit_kundu_io.compare.domain.Repository.DeveloperRepository
import com.amit_kundu_io.compare.domain.use_case.GetDeveloperStatsUseCase.CalculateDeveloperScoreUseCase
import com.amit_kundu_io.compare.domain.use_case.GetDeveloperStatsUseCase.CompareDevelopersUseCase
import com.amit_kundu_io.compare.domain.use_case.GetDeveloperStatsUseCase.GetDeveloperStatsUseCase
import com.amit_kundu_io.compare.presentation.CompareViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val developerModule = module {

    single<DeveloperApi> {
        DeveloperApi(
            client = get()
        )
    }

    single<DeveloperRepository> {
        DeveloperRepositoryImpl(
            api = get()
        )
    }

    factory {
        GetDeveloperStatsUseCase(
            repository = get()
        )
    }

    factory {
        CalculateDeveloperScoreUseCase()
    }

    factory {
        CompareDevelopersUseCase(
            getDeveloperStatsUseCase = get(),
            calculateDeveloperScoreUseCase = get(),
        )
    }

    viewModelOf(::CompareViewModel)
}