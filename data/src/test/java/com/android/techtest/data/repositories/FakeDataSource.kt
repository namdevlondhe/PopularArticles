package com.android.techtest.data.repositories

import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.repositories.ArticleRepository
import com.android.techtest.domain.util.Resource

class FakeDataSource : ArticleRepository{
    override suspend fun getArticleList(period: Int): Resource<ArticleCharacter> {
        return Resource.success(null)
    }
}