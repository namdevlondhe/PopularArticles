package com.example.bookmyshow.repository

import com.android.techtest.domain.entities.ArticleResponse
import com.android.techtest.domain.repositories.ArticleRepository
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Resource

class FakeDataSource(): ArticleRepository{
    override suspend fun getArticleList(period: Int): Resource<ArticleResponse> {
        return Resource.success(null)
    }
}