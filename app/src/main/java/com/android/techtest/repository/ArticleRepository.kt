package com.android.techtest.repository

import com.android.techtest.api.ApiService
import com.android.techtest.model.ArticleResponse
import retrofit2.Response

interface ArticleRepository {
   suspend fun getArticleList(period:Int):Response<ArticleResponse>
}

class ArticleRepositoryImpl(private val apiService: ApiService) : ArticleRepository{

    override suspend fun getArticleList(period: Int): Response<ArticleResponse> {
        return apiService.getArticleList(period)
    }

}