package com.android.techtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.techtest.model.ArticleResponse
import com.android.techtest.model.Result
import com.android.techtest.repository.ArticleRepository
import com.android.techtest.util.Resource
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val mArticleRepository: ArticleRepository, application: Application
) : AndroidViewModel(application) {

    var period: Int = 7

    private val mList = MutableLiveData<Resource<ArticleResponse>>()
    val aList: LiveData<Resource<ArticleResponse>> get() = mList

    lateinit var dataItem: Result

    fun fetchArticleList() {
        viewModelScope.launch {
            mList.postValue(Resource.loading(null))
            mArticleRepository.getArticleList(period).let { response ->
                mList.postValue(response)
            }
        }
    }

    fun setCurrentArticle(item: Result) {
        dataItem = item
    }
}