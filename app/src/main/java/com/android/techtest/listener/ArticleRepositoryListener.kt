package com.android.techtest.listener
 import com.android.techtest.model.Result

interface ArticleRepositoryListener {
    fun onSuccess(mutableList: MutableList<Result>)

    fun onArticleDetailsFetched(article: Result)
}