package com.example.namariaanimation

import android.app.Application
import com.example.weatherapi.networkModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NamariaApplication: Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidLogger()
            androidContext(this@NamariaApplication)
            modules(appModules, networkModules)
        }
    }
}