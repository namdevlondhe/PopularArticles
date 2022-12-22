package com.android.techtest.domain.repositories

import com.android.techtest.domain.entities.ArticleResponse
import com.android.techtest.domain.util.Resource

interface ArticleRepository {
    suspend fun getArticleList(period:Int): Resource<ArticleResponse>
}