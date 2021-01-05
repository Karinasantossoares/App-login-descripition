package com.example.app_description_apiary.ui

import android.app.Application
import com.example.app_description_apiary.repository.repositoryModule
import com.example.app_description_apiary.repository.serviceModule
import com.example.app_description_apiary.repository.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(viewModelModule, repositoryModule, serviceModule)
        }
    }
}