package com.android.techtest.repository

import com.android.techtest.api.ApiService
import com.android.techtest.model.ArticleResponse
import com.android.techtest.util.AppConstants.NETWORK_ERROR
import com.android.techtest.util.NetworkHelper
import com.android.techtest.util.Resource

class ArticleRepository(private val apiService: ApiService, private val mNetworkHelper: NetworkHelper) :
    ArticleRepositoryUseCases {

    override suspend fun getArticleList(period: Int): Resource<ArticleResponse> {
        return if (mNetworkHelper.isNetworkConnected()) {
            val response = apiService.getArticleList(period)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.errorBody().toString(), null)
            }
        }else{
            Resource.error(NETWORK_ERROR, null)
        }
    }

}