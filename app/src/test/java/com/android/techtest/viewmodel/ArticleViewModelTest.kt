package com.android.techtest.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ActivityScenario.launch
import com.android.techtest.ArticleApplication
import com.android.techtest.model.ArticleResponse
import com.android.techtest.repository.ArticleRepository
import com.android.techtest.util.NetworkHelper
import com.android.techtest.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.jupiter.api.BeforeAll
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ArticleViewModelTest {
    val mArticleRepository: ArticleRepository = Mockito.mock(ArticleRepository::class.java)
    val mNetworkHelper: NetworkHelper = Mockito.mock(NetworkHelper::class.java)
    val application: Application = Mockito.mock(ArticleApplication::class.java)
    private val viewModel:ArticleViewModel = ArticleViewModel(mArticleRepository,mNetworkHelper,application)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @BeforeAll
    private fun doOnBeforeAll() {
        MockitoAnnotations.openMocks(this)
    }
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun fetchArticleListTestSuccess(){
        var skeletonResponse:Resource<ArticleResponse>? = null
        viewModel.fetchArticleList()
        viewModel.aList.observeForever {
            skeletonResponse = it
        }
        Assert.assertNull(skeletonResponse)
    }

    @Test
    fun fetchArticleListTestFailed(){
        var skeletonResponse :Resource<ArticleResponse>? = null
        viewModel.period = 0
        viewModel.fetchArticleList()
        viewModel.aList.observeForever {
            skeletonResponse = it
        }

        Assert.assertNotNull(skeletonResponse.toString(),Resource.error("{\"fault\":{\"faultstring\":\"Failed to resolve API Key variable request.queryparam.api-key\",\"detail\":{\"errorcode\":\"steps.oauth.v2.FailedToResolveAPIKey\"}}}", null))
    }
}