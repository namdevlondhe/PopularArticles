package com.android.techtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.android.techtest.data.entities.ArticleResponse
import com.android.techtest.data.entities.Result
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Resource
import com.android.techtest.util.Constants.PERIOD
import com.android.techtest.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val articleUseCases: GetArticleUseCases
) : BaseViewModel() {

    val articleList = MutableLiveData<Resource<ArticleResponse>>()
    lateinit var dataItem: Result

    fun fetchArticleList() {
        viewModelScope.launch {
            articleList.postValue(Resource.loading(null))

            articleUseCases.invoke(PERIOD).onStart {
                articleList.value = Resource.loading(null)
            }.catch {
                articleList.value = it.message?.let { it1 -> Resource.error(it1, null) }
            }.collect {
                articleList.value = Resource.success(it.data) as Resource<ArticleResponse>?
            }
        }
    }

    fun setCurrentArticle(item: Result) {
        dataItem = item
    }

    class Factory(
        private val articleUseCases: GetArticleUseCases
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleViewModel(articleUseCases) as T
        }
    }
}