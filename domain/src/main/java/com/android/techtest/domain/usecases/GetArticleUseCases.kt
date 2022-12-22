package com.android.techtest.domain.usecases

import com.android.techtest.domain.repositories.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class GetArticleUseCases : KoinComponent {
    //If we provide constructor dependency then we have to pass the repository from ui as well.
    private val articleRepository: ArticleRepository by inject()

    suspend operator fun invoke(word: Int) =
        withContext(Dispatchers.Main) {
            flow { // will be executed on IO if context wasn't specified before
                emit(articleRepository.getArticleList(word))
                // Will be executed in IO
            }.flowOn(Dispatchers.IO) // This one takes precedence
        }
}