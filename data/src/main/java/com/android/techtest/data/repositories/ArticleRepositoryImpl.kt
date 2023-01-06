package com.android.techtest.data.repositories

import com.android.techtest.data.mapper.ArticleResponseMapper
import com.android.techtest.data.service.api.ApiService
import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.repositories.ArticleRepository
import com.android.techtest.domain.util.Resource

class ArticleRepositoryImpl(
    private val apiService: ApiService,
    private val responseMapper: ArticleResponseMapper = ArticleResponseMapper()
) : ArticleRepository {

    override suspend fun getArticleList(period: Int): Resource<ArticleCharacter> {
        val response = apiService.getArticleList(period).takeIf { it.isSuccessful }
        return response?.body()?.let { resultResponse ->
            Resource.Success(responseMapper.transform(resultResponse)
            )
        } ?: Resource.Error( response?.errorBody().toString())
    }
}