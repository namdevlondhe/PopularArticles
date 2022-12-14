package com.android.techtest.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.techtest.ArticleApplication
import com.android.techtest.model.ArticleResponse
import com.android.techtest.repository.ArticleRepository
import com.android.techtest.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ArticleViewModelTest {
    private val mArticleRepository: ArticleRepository = Mockito.mock(ArticleRepository::class.java)
    private val application: Application = Mockito.mock(ArticleApplication::class.java)
    private val viewModel:ArticleViewModel = ArticleViewModel(mArticleRepository,application)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun fetchArticleListTestSuccess()= runTest(UnconfinedTestDispatcher()){
        var skeletonResponse:Resource<ArticleResponse>? = null
        viewModel.fetchArticleList()
        viewModel.aList.observeForever {
            skeletonResponse = it
        }
        Assert.assertNotNull(skeletonResponse)
    }

    @Test
    fun fetchArticleListTestFailed() = runTest(UnconfinedTestDispatcher()){
        var skeletonResponse :Resource<ArticleResponse>? = null
        viewModel.period = 0
        viewModel.fetchArticleList()
        viewModel.aList.observeForever {
            skeletonResponse = it
        }

        Assert.assertNotNull(skeletonResponse.toString(),Resource.error("{\"fault\":{\"faultstring\":\"Failed to resolve API Key variable request.queryparam.api-key\",\"detail\":{\"errorcode\":\"steps.oauth.v2.FailedToResolveAPIKey\"}}}", null))
    }
}