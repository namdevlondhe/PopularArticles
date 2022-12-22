package com.android.techtest.domain.usecases

import com.android.techtest.domain.repositories.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class GetArticleUseCases : KoinComponent {
    private val articleRepository: ArticleRepository by inject()

    suspend operator fun invoke(word: Int) = flow {

        emit(articleRepository.getArticleList(word))
    }.flowOn(Dispatchers.IO)

}