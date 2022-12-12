package com.android.techtest.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.android.techtest.R
import com.android.techtest.model.ArticleResponse
import com.android.techtest.model.Result
import com.android.techtest.repository.ArticleRepository
import com.android.techtest.util.NetworkHelper
import com.android.techtest.util.Resource
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val mArticleRepository: ArticleRepository,
    private val mNetworkHelper: NetworkHelper, application: Application
) : AndroidViewModel(application) {

    var period: Int = 7

    private val mList = MutableLiveData<Resource<ArticleResponse>>()
    val aList: LiveData<Resource<ArticleResponse>> get() = mList

    lateinit var dataItem: Result

    fun fetchArticleList() {
        viewModelScope.launch {
            mList.postValue(Resource.loading(null))
            mArticleRepository.getArticleList(
                getApplication<Application>().applicationContext,
                period
            ).let {
                mList.postValue(it)
            }
        }
    }

    fun setCurrentArticle(item: Result) {
        dataItem = item
    }
}