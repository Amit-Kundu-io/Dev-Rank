package com.kundutechstudio.profile.di

import com.kundutechstudio.profile.presentation.PrefileScreen.PrefileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun profileModule() = module {

    viewModelOf(::PrefileViewModel)
}