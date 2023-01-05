package com.android.techtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.techtest.util.NetworkHelper
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Resource
import com.android.techtest.domain.util.Status
import com.android.techtest.entities.ArticleData
import com.android.techtest.mapper.ArticleCharacterMapper
import com.android.techtest.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val articleUseCases: GetArticleUseCases,
    private val articleCharacterMapper: ArticleCharacterMapper = ArticleCharacterMapper(),
    private val networkHelper: NetworkHelper,
) : BaseViewModel() {

    private var _articleList = MutableLiveData<Resource<ArticleData>>()
    val articleList: LiveData<Resource<ArticleData>> = _articleList

    fun fetchArticleList() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                articleUseCases().onStart {
                    _articleList.value = Resource.Loading(Status.LOADING)
                }.catch { result ->
                    _articleList.value =
                        result.message?.let { it1 -> Resource.Error(Status.ERROR, it1) }
                }.collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data.let {
                                _articleList.value = Resource.Success(
                                    Status.SUCCESS,
                                    articleCharacterMapper.transform(it)
                                )
                            }
                        }
                        is Resource.Error -> {
                            _articleList.value =
                                result.message.let { it1 -> Resource.Error(Status.ERROR, it1) }
                        }
                        else -> {}
                    }

                }
            } else {
                _articleList.value = Resource.Error(Status.ERROR, "NETWORK_ERROR")
            }
        }
    }
}