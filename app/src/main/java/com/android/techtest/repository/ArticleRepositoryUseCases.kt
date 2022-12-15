package com.android.techtest.repository

import com.android.techtest.model.ArticleResponse
import com.android.techtest.util.Resource

interface ArticleRepositoryUseCases {
    suspend fun getArticleList(period:Int): Resource<ArticleResponse>
}