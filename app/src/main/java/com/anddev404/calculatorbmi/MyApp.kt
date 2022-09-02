package com.anddev404.calculatorbmi

import android.app.Application
import com.anddev404.calculatorbmi.di.repositoryModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(repositoryModules))
        }
    }
}