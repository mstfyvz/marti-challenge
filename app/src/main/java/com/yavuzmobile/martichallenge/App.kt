package com.yavuzmobile.martichallenge

import androidx.multidex.MultiDexApplication
import com.yavuzmobile.martichallenge.di.modules
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }
}