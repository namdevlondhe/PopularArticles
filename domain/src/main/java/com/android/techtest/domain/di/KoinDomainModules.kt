package com.android.techtest.domain.di

import com.android.techtest.domain.usecases.GetArticleUseCases
import org.koin.core.qualifier.named
import org.koin.dsl.module
import javax.inject.Singleton

@Singleton
val appDomainModule = module {
    single { GetArticleUseCases(get(), get(named("IODispatcher"))) }
}