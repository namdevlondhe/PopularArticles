package com.android.techtest.data.repositories

import com.android.techtest.data.service.api.ApiService
import com.android.techtest.data.util.NetworkHelper
import com.android.techtest.data.entities.ArticleResponse
import com.android.techtest.domain.repositories.ArticleRepository
import com.android.techtest.domain.util.Resource

class ArticleRepositoryImpl(
    private val apiService: ApiService,
    private val mNetworkHelper: NetworkHelper
    ) : ArticleRepository {

    override suspend fun getArticleList(period: Int): Resource<ArticleResponse> {
        return if (mNetworkHelper.isNetworkConnected()) {
            val response = apiService.getArticleList(period)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.errorBody().toString(), null)
            }
        }else{
            Resource.error("NETWORK_ERROR", null)
        }
    }

}