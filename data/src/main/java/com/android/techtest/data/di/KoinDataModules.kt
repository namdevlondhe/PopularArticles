package com.android.techtest.data.di

import com.android.techtest.data.repositories.ArticleRepositoryImpl
import com.android.techtest.domain.repositories.ArticleRepository
import org.koin.dsl.module
import javax.inject.Singleton

@Singleton
val appDadaModules = module {

    // single instance of ArticleRepository
    single<ArticleRepository> { ArticleRepositoryImpl(get(), get()) }
}