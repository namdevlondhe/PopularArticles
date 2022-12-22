package com.android.techtest.data.service.api

import com.android.techtest.data.BuildConfig.NYTimesApiKey
import com.android.techtest.domain.entities.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("svc/mostpopular/v2/viewed/{period}.json?")
    suspend fun getArticleList(@Path("period") period: Int,@Query("api-key") key:String = NYTimesApiKey):Response<ArticleResponse>

}