package com.android.techtest.di

import android.content.Context
import com.android.techtest.mapper.ArticleCharacterMapper
import com.android.techtest.util.NetworkHelper
import com.android.techtest.viewmodel.ArticleViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import javax.inject.Singleton

@Singleton
val appModule = module {

    single { ArticleCharacterMapper() }

    viewModel { ArticleViewModel(get(), get(), get()) }

    single(named("IODispatcher")) {
        Dispatchers.IO
    }

    single(named("MainDispatcher")) {
        Dispatchers.Main
    }

    single { provideNetworkHelper(androidContext()) }
}

fun provideNetworkHelper(context: Context) = NetworkHelper(context)