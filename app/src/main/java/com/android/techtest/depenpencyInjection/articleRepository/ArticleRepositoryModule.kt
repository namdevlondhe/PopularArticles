package com.android.techtest.depenpencyInjection.articleRepository

import android.app.Application
import com.android.techtest.listener.ArticleRepositoryListener
import dagger.Module

@Module
class ArticleRepositoryModule(
    private val application: Application,
    private val articleRepositoryListener: ArticleRepositoryListener
) {

    /*@Provides
    private fun providesArticleRepository() {
    }*/

}