package com.android.techtest.viewmodel

import android.app.Application
import com.android.techtest.ArticleApplication
import com.android.techtest.repository.ArticleRepository
import com.android.techtest.util.FileUtils.getJson
import com.android.techtest.util.NetworkHelper
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito


class ArticleViewModelTest {
    val mArticleRepository: ArticleRepository = Mockito.mock(ArticleRepository::class.java)
    val mNetworkHelper: NetworkHelper = Mockito.mock(NetworkHelper::class.java)
    val application: Application = Mockito.mock(ArticleApplication::class.java)
    private val viewModel:ArticleViewModel = ArticleViewModel(mArticleRepository,mNetworkHelper,application)

    @Test
    fun fetchArticleListTestSuccess(){
        val skeletonResponse = getJson("json/ArticleResults.json")
        val articleViewState = viewModel.fetchArticleList()
        Assert.assertEquals(skeletonResponse,articleViewState)
    }

    @Test
    fun fetchArticleListTestFailed(){
        val skeletonResponse = getJson("json/ArticleResultsFailed.json")
        viewModel.period = 0
        val articleViewState = viewModel.fetchArticleList()
        Assert.assertEquals(skeletonResponse,articleViewState)
    }
}