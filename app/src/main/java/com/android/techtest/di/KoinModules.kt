package com.android.techtest.di

import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import javax.inject.Singleton


@Singleton
val appModule = module {

    viewModel { ArticleViewModel(get()) }

}

@Singleton
val useCasesModule = module {
    single { GetArticleUseCases() }
}


