package com.android.techtest.data.repositories

import com.android.techtest.data.entities.ArticleResponse
import com.android.techtest.domain.repositories.ArticleRepository
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeRepository(repository:ArticleRepository) : GetArticleUseCases() {

    private val flow = MutableSharedFlow<Resource<ArticleResponse>>()
    suspend fun emit(value: Resource<ArticleResponse>) = flow.emit(value)
    fun getArticles(): Flow<Resource<ArticleResponse>> = flow
}
