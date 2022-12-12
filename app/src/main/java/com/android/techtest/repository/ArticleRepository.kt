package com.android.techtest.repository

import android.app.Application
import android.content.Context
import com.android.techtest.R
import com.android.techtest.api.ApiService
import com.android.techtest.model.ArticleResponse
import com.android.techtest.util.NetworkHelper
import com.android.techtest.util.Resource
import retrofit2.Response

interface ArticleRepository {
   suspend fun getArticleList(context: Context,period:Int):Resource<ArticleResponse>
}

class ArticleRepositoryImpl(private val apiService: ApiService,private val mNetworkHelper: NetworkHelper) :
    ArticleRepository {

    override suspend fun getArticleList(context: Context, period: Int): Resource<ArticleResponse> {
        return if (mNetworkHelper.isNetworkConnected()) {
            val response = apiService.getArticleList(period)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.errorBody().toString(), null)
            }
        }else{
            Resource.error(context.applicationContext.getString(R.string.str_connection_error), null)
        }
    }

}