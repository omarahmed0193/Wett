package com.afterapps.wett

import android.app.Application
import com.afterapps.wett.di.dataModule
import com.afterapps.wett.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WettApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Initialize koin
        startKoin {
            androidContext(baseContext)
            modules(dataModule, homeModule)
        }
    }
}