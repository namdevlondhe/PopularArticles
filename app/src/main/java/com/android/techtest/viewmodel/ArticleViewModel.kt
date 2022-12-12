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
) :AndroidViewModel(application) {

    var period:Int = 7

    private val mList = MutableLiveData<Resource<ArticleResponse>>()
    val aList: LiveData<Resource<ArticleResponse>> get() = mList

    lateinit var dataItem:Result

    fun fetchArticleList(): String? {
        var response:String? = null
        viewModelScope.launch {
            mList.postValue(Resource.loading(null))
            if (mNetworkHelper.isNetworkConnected()) {
                mArticleRepository.getArticleList(period).let {
                    if (it.isSuccessful) {
                        mList.postValue(Resource.success(it.body()))
                        response = it.body().toString()
                    } else {
                        mList.postValue(Resource.error(it.errorBody().toString(), null))
                        response = it.errorBody().toString()
                    }
                }
            } else {
                mList.postValue(Resource.error(getApplication<Application>().applicationContext.getString(R.string.str_connection_error), null))
                response =getApplication<Application>().applicationContext.getString(R.string.str_connection_error)
            }
        }
        return response
    }

    fun setCurrentArticle(item: Result) {
        dataItem = item
    }
}