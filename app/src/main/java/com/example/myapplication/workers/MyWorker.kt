package com.example.myapplication.workers

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.myapplication.widget.MyWidget
import timber.log.Timber

class MyWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        try {
            Timber.e("Do some work")
            MyWidget().updateAll(applicationContext)
            val manager = GlanceAppWidgetManager(applicationContext)
            return Result.success()
        } catch (th: Throwable) {
            return Result.failure()
        }
    }
}
