package com.farad.entertainment.aramkada.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.farad.entertainment.aramkada.di.appModule
import com.farad.entertainment.aramkada.di.dataSourceModule
import com.farad.entertainment.aramkada.di.networkModule
import com.farad.entertainment.aramkada.di.repositoryModule
import com.farad.entertainment.aramkada.di.restModule
import com.farad.entertainment.aramkada.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initModule()
    }

    private fun initModule() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(
                appModule,
                networkModule,
                repositoryModule,
                dataSourceModule,
                restModule,
                viewModelModule,
                module {
                    single { this@BaseApp.contentResolver }
                }
            )
        }
    }

}