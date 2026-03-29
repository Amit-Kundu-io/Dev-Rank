package com.kundutechstudio.auth.di

import com.kundutechstudio.auth.data.repo_impl.AuthRepository
import com.kundutechstudio.auth.presentation.login.LoginViewModel
import com.kundutechstudio.auth.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun authModules() = module {
    singleOf(::AuthRepository)
    viewModelOf(::LoginViewModel)
    viewModelOf(::SplashViewModel)
}