package com.android.techtest.depenpencyInjection.articleRepository

import com.android.techtest.viewmodel.ArticleViewModel
import dagger.Component

@Component (modules = [ArticleRepositoryModule::class])
interface ArticleRepositoryComponent {

    fun inject(articleViewModel: ArticleViewModel)
}