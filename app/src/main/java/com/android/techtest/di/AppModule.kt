package com.android.techtest.di

import android.content.Context
import com.android.techtest.api.ApiService
import com.android.techtest.repository.ArticleRepository
import com.android.techtest.util.AppConstants
import com.android.techtest.util.NetworkHelper
import com.android.techtest.viewmodel.ArticleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
            Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

    }
    // single instance of HelloRepository
    single<ArticleRepository> { ArticleRepository(get(), get()) }
    single { provideNetworkHelper(androidContext()) }

    viewModel { ArticleViewModel(get(),get()) }

}

fun provideNetworkHelper(context: Context) = NetworkHelper(context)

