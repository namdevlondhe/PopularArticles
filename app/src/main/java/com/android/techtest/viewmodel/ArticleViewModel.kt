package com.android.techtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.techtest.util.NetworkHelper
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Resource
import com.android.techtest.entities.ArticleData
import com.android.techtest.mapper.ArticleCharacterMapper
import com.android.techtest.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val articleUseCases: GetArticleUseCases,
    private val articleCharacterMapper: ArticleCharacterMapper = ArticleCharacterMapper(),
    private val networkHelper: NetworkHelper,
) : BaseViewModel() {

    private val _articleList = MutableStateFlow<Resource<ArticleData>>(Resource.Loading)
    val articleList: StateFlow<Resource<ArticleData>> = _articleList

    fun fetchArticleList() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                _articleList.value = Resource.Loading
                articleUseCases().onStart {
                    _articleList.value = Resource.Loading
                }.catch { e ->
                    _articleList.value = Resource.Error(e.toString())
                }.collect {
                    if (it is Resource.Success) {
                        _articleList.value =
                            Resource.Success(articleCharacterMapper.transform(it.data))
                    }
                }
            } else {
                _articleList.value = Resource.Error("NETWORK_ERROR")
            }
        }
    }
}