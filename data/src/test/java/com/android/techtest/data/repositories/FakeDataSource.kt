package com.android.techtest.data.repositories

import com.android.techtest.data.entities.ArticleResponse
import com.android.techtest.domain.repositories.ArticleRepository
import com.android.techtest.domain.util.Resource

class FakeDataSource : ArticleRepository{
    override suspend fun getArticleList(period: Int): Resource<ArticleResponse> {
        return Resource.success(null)
    }
}