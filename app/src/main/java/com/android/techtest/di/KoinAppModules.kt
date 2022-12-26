package com.android.techtest.di

import com.android.techtest.viewmodel.ArticleViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import javax.inject.Singleton

@Singleton
val appModule = module {
    viewModel { ArticleViewModel(get()) }

    single(named("IODispatcher")) {
        Dispatchers.IO
    }

    single(named("MainDispatcher")) {
        Dispatchers.IO
    }
}