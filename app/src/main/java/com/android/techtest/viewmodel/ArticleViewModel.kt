package com.android.techtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.techtest.data.entities.Result
import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Resource
import com.android.techtest.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val articleUseCases: GetArticleUseCases
) : BaseViewModel() {

    private var _articleList = MutableLiveData<Resource<ArticleCharacter>>()
    val articleList: LiveData<Resource<ArticleCharacter>> = _articleList

    fun fetchArticleList() {
        viewModelScope.launch {
            _articleList.postValue(Resource.loading(null))

            articleUseCases().onStart {
                _articleList.value = Resource.loading(null)
            }.catch { result ->
                _articleList.value = result.message?.let { it1 -> Resource.error(it1, null) }
            }.collect { result ->
                result.data?.let {
                    _articleList.value = Resource.success(it)
                }
            }
        }
    }
}