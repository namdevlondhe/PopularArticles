package com.android.techtest

import android.app.Application
import com.android.techtest.di.appModule
import com.android.techtest.di.applicationModule
import com.android.techtest.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArticleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidContext(this@ArticleApplication)
            modules(listOf(applicationModule, appModule, useCasesModule))
        }
    }
}