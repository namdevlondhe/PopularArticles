package com.android.techtest.data.repositories

import com.android.techtest.data.service.api.ApiService
import com.android.techtest.data.util.NetworkHelper
import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.repositories.ArticleRepository
import com.android.techtest.domain.util.Resource

class ArticleRepositoryImpl(
    private val apiService: ApiService,
    private val mNetworkHelper: NetworkHelper
) : ArticleRepository {

    override suspend fun getArticleList(period: Int): Resource<ArticleCharacter> {
        return if (mNetworkHelper.isNetworkConnected()) {
            val response = apiService.getArticleList(period).takeIf { it.isSuccessful }
            response?.body()?.let { resultResponse ->
                Resource.success(
                    ArticleCharacter(
                        resultResponse.copyright,
                        resultResponse.numResults,
                        resultResponse.results,
                        resultResponse.status
                    )
                )
            } ?: Resource.error(response?.errorBody().toString(), null)
        } else {
            Resource.error("NETWORK_ERROR", null)
        }
    }
}