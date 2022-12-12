package com.android.techtest.viewmodel

import android.app.Application
import com.android.techtest.ArticleApplication
import com.android.techtest.model.ArticleResponse
import com.android.techtest.repository.ArticleRepository
import com.android.techtest.util.FileUtils.getJson
import com.android.techtest.util.NetworkHelper
import com.android.techtest.util.Resource
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.BeforeAll
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class ArticleViewModelTest {
    val mArticleRepository: ArticleRepository = Mockito.mock(ArticleRepository::class.java)
    val mNetworkHelper: NetworkHelper = Mockito.mock(NetworkHelper::class.java)
    val application: Application = Mockito.mock(ArticleApplication::class.java)
    private val viewModel:ArticleViewModel = ArticleViewModel(mArticleRepository,mNetworkHelper,application)

    @BeforeAll
    private fun doOnBeforeAll() {
        MockitoAnnotations.openMocks(this)
    }
    @Test
    fun fetchArticleListTestSuccess(){
        var skeletonResponse:Resource<ArticleResponse>? = null
        viewModel.fetchArticleList()
        viewModel.aList.observeForever {
            skeletonResponse = it
        }
        Assert.assertNotNull(skeletonResponse)
    }

    @Test
    fun fetchArticleListTestFailed(){
        val skeletonResponse = getJson("json/ArticleResultsFailed.json")
        viewModel.period = 0
        val articleViewState = viewModel.fetchArticleList()
        Assert.assertEquals(skeletonResponse,articleViewState)
    }
}