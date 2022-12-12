package com.android.techtest.api

import com.android.techtest.BuildConfig
import com.android.techtest.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("svc/mostpopular/v2/viewed/{period}.json?")
    suspend fun getArticleList(@Path("period") period: Int,@Query("api-key") key:String = BuildConfig.NYTimesApiKey):Response<ArticleResponse>

}