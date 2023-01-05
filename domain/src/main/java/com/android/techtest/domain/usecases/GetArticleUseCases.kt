package com.android.techtest.domain.usecases

import com.android.techtest.domain.repositories.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetArticleUseCases(
    private val articleRepository: ArticleRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        word: Int = PERIOD
    ) = flow {
        emit(articleRepository.getArticleList(word))
    }.flowOn(ioDispatcher) // This one takes precedence

    private companion object {
        const val PERIOD = 7
    }
}