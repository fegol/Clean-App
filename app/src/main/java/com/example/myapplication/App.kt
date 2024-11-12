package com.example.myapplication

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.getSystemService
import com.example.myapplication.di.appModule
import com.example.myapplication.notification.NotificationHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        getSystemService<NotificationManager>()?.let {
            NotificationHelper.createChannels(it)
        }
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }
    }
}