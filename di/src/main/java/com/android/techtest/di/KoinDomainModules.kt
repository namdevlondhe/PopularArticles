package com.android.techtest.di

import android.content.Context
import com.android.techtest.data.repositories.ArticleRepositoryImpl
import com.android.techtest.data.service.api.ApiService
import com.android.techtest.data.util.ApiConstants
import com.android.techtest.data.util.NetworkHelper
import com.android.techtest.domain.repositories.ArticleRepository
import com.android.techtest.domain.usecases.GetArticleUseCases
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
val appDomainApiModule = module {

    single {
            Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

    }

    single { provideNetworkHelper(androidContext()) }
}

@Singleton
val appDomainUseCasesModule = module {
    single { GetArticleUseCases(get(), get(named("IODispatcher"))) }
}

fun provideNetworkHelper(context: Context) = NetworkHelper(context)

