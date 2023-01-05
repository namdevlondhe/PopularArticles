package com.android.techtest.data.di

import android.content.Context
import com.android.techtest.data.mapper.ArticleResponseMapper
import com.android.techtest.data.repositories.ArticleRepositoryImpl
import com.android.techtest.data.service.api.ApiService
import com.android.techtest.data.util.ApiConstants
import com.android.techtest.data.util.NetworkHelper
import com.android.techtest.domain.repositories.ArticleRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
val appDadaModules = module {

    single { ArticleResponseMapper() }

    // single instance of ArticleRepository
    single<ArticleRepository> { ArticleRepositoryImpl(get(), get(), get()) }

    single {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single { provideNetworkHelper(androidContext()) }
}

fun provideNetworkHelper(context: Context) = NetworkHelper(context)
