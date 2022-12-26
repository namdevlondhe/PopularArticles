package com.android.techtest.domain.repositories

import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.util.Resource

interface ArticleRepository {
    suspend fun getArticleList(period:Int): Resource<ArticleCharacter>
}