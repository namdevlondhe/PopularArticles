package com.android.techtest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.techtest.api.ApiService
import com.android.techtest.model.ArticleResponse
import com.android.techtest.util.NetworkHelper
import com.android.techtest.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ArticleRepositoryImplTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val apiService: ApiService = Mockito.mock(ApiService::class.java)
    private val mNetworkHelper: NetworkHelper = Mockito.mock(NetworkHelper::class.java)
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun checkNetworkConnectivityFailed(){
        val skeletonResult = false
        val result = mNetworkHelper.isNetworkConnected()
        Assert.assertEquals(skeletonResult,result)
    }

    @Test
    fun checkNetworkConnectivitySuccess(){
        val skeletonResult = true
        val result = mNetworkHelper.isNetworkConnected()
        Assert.assertEquals(skeletonResult,result)
    }

    @Test
    suspend fun getArticleList() {
        val skeletonResponse: Resource<ArticleResponse>? = null
        val samplePeriod = 7
        val response = apiService.getArticleList(samplePeriod)
        Assert.assertNotEquals(skeletonResponse,response)
    }
}