package com.android.techtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Resource
import com.android.techtest.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class ArticleViewModelTest {
    @MockK
    private lateinit var viewModel: ArticleViewModel

    private val articleUseCases = mockk<GetArticleUseCases>(relaxed = true)

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() = runBlocking {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        unmockkAll()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get Article List`() = runBlockingTest {
        val response = mockk<Resource<ArticleCharacter>>(relaxed = true)
        launch {
            viewModel = ArticleViewModel(articleUseCases)
            //1- Mock calls
            coEvery {
                viewModel.fetchArticleList()
                articleUseCases.invoke(7)
            } returns flow {
                emit(Resource.success(response.data!!))

                //2-Call

                //active observer for livedata
                viewModel.articleList.observeForever {}

                //3-verify
                val data = viewModel.articleList.value
                assertEquals(data, response)
            }
        }
    }

    @Test
    fun `get Article Empty List`(): Unit = runBlocking {
        val response = mockk<Resource<ArticleCharacter>>()
        viewModel = ArticleViewModel(articleUseCases)
        //1- Mock calls
        launch {
            coEvery {
                articleUseCases.invoke(0)
            } returns flow {
                emit(Resource.success(response.data!!))

                //2-Call
                viewModel.fetchArticleList()

                //active observer for livedata
                viewModel.articleList.observeForever {}

                //3-verify
                val data = viewModel.articleList.value
                val isEmptyList = data?.data?.results.isNullOrEmpty()
                assertEquals(data, response)
                Assert.assertTrue(isEmptyList)
            }
        }
    }

    @Test
    fun `get Article Error`(): Unit = runBlocking {
        val error = mockk<Resource<ArticleCharacter>>()
        viewModel = ArticleViewModel(articleUseCases)
        //1- Mock calls
        launch {
            coEvery {
                articleUseCases.invoke(0)
            } returns flow {
                emit(Resource.error(error.message!!, null))

                //2-Call
                viewModel.fetchArticleList()
                //active observer for livedata
                viewModel.articleList.observeForever {}

                //3-verify
                val data = viewModel.articleList.value
                assertEquals(data?.message, null)
            }
        }
    }
}