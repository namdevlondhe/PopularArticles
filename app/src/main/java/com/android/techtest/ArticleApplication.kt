package com.android.techtest

import android.app.Application
import com.android.techtest.data.di.appDadaModules
import com.android.techtest.domain.di.appDomainModule
import com.android.techtest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArticleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidContext(this@ArticleApplication)
            modules(
                listOf(
                    appModule,
                    appDomainModule,
                    appDadaModules
                )
            )
        }
    }
}