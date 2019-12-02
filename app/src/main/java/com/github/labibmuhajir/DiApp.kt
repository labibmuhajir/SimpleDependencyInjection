package com.github.labibmuhajir

import android.app.Application
import com.github.labibmuhajir.di.InjectEngine
import com.github.labibmuhajir.di.appModule
import com.github.labibmuhajir.di.repositoryModule

class DiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        InjectEngine.applicationContext = applicationContext
        InjectEngine.apply {
            addModules(appModule)
            addModules(repositoryModule)
            addSpecificModule("cinema", "big screen cinema")
            addSpecificModule("address", "jakarta")
        }
    }
}