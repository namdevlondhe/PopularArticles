package com.android.techtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Resource
import com.android.techtest.domain.util.Status
import com.android.techtest.util.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticleViewModelTest {
    @Mock
    private var viewModel: ArticleViewModel= mockk()

    private val articleUseCases: GetArticleUseCases = mockk()

    @ExperimentalCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup()= runBlocking {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() = runBlocking{
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get Article List`(): Unit = runBlocking {
        val response = mockk<Resource<ArticleCharacter>>()
        viewModel = ArticleViewModel(articleUseCases)
        launch {
            viewModel.fetchArticleList()
            //1- Mock calls

            coEvery {
                articleUseCases.invoke(7)
            } returns flow {
                emit(Resource.success(response.data!!))
            }

            //2-Call

        //active observer for livedata
        viewModel.articleList.observeForever {}

        //3-verify
        val data = viewModel.articleList.value
        assertEquals(data, response)
        }
    }

    @Test
    fun `get Article Empty List`() = runBlocking {
        val response = mockk<Resource<ArticleCharacter>>()
        //response.data?.results = emptyList<com.android.techtest.data.entities.Result>()

        //1- Mock calls
        launch {
            coEvery {
                articleUseCases.invoke(0)
            } returns flow {
                emit(Resource.success(response.data!!))
            }

            //2-Call
            viewModel = ArticleViewModel(articleUseCases)
            viewModel.fetchArticleList()
        }
        //active observer for livedata
        viewModel.articleList.observeForever {}

        //3-verify
        val data = viewModel.articleList.value
        val isEmptyList = data?.data?.results.isNullOrEmpty()
        assertEquals(data, response)
        Assert.assertTrue(isEmptyList)
    }

    @Test
    fun `get Article Error`() = runBlocking {
        val error = mockk<Resource<ArticleCharacter>>()
        error.status = Status.ERROR
        error.message = "Failed"
        //1- Mock calls
        launch {
            coEvery {
                articleUseCases.invoke(0)
            } returns flow {
                emit(Resource.error(error.message!!, null))
            }

            //2-Call
            viewModel = ArticleViewModel(articleUseCases)
            viewModel.fetchArticleList()
            //active observer for livedata
            viewModel.articleList.observeForever {}
        }
        //3-verify
        val data = viewModel.articleList.value
        Assert.assertEquals(data?.message, "Failed")
    }
}