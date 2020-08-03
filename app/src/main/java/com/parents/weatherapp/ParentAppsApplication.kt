package com.parents.weatherapp

import android.app.Application
import com.parents.weatherapp.di.appModule
import com.parents.weatherapp.di.localModule
import com.parents.weatherapp.di.networkModule
import com.parents.weatherapp.di.weatherModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Ahmed Zain on 8/2/2020.
 */
class ParentAppsApplication : Application() {
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ParentAppsApplication)
            modules(
                listOf(
                    networkModule,
                    appModule,
                    localModule,
                    weatherModule
                )
            )
        }
    }
}