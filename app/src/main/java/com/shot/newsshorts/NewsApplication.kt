package com.shot.newsshorts

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"COMING INSIDE ONCREATE")
    }

    companion object{
        const val TAG = "NewsApplication"
    }
}