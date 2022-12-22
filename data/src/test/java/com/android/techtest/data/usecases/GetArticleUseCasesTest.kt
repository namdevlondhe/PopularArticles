package com.android.techtest.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.techtest.data.CoroutinesTestRule
import com.android.techtest.domain.util.Status
import com.example.bookmyshow.repository.FakeDataSource
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class GetArticleUseCasesTest {
    private lateinit var mTheaterUseCase: GetArticleUseCases
    lateinit var fakeDataSource: FakeDataSource

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start() {
        startKoin { }
        fakeDataSource = FakeDataSource()
        mTheaterUseCase = GetArticleUseCases()

    }

    @After
    fun stop() {
        stopKoin()
    }

    @Test
    fun test_theaters_use_case_returns_loading() = runBlocking {
        var progress = ""
        mTheaterUseCase.invoke(7).collect {
            if (it.status == Status.LOADING) {
                progress = "Loading"
            }
        }
        Assert.assertEquals("Loading", progress)
    }

    @Test
    fun test_theaters_use_case_returns_expected_value() = runBlocking {
        var progress = ""
        mTheaterUseCase.invoke(0).collect() {
            if (it.status == Status.SUCCESS) {
                progress = "Success"
            } else if (it.status == Status.LOADING) {
                progress = "Loading"
            }
        }
        Assert.assertEquals("Success", progress)
    }


}