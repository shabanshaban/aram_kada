package com.farad.entertainment.aramkada.di

import android.os.Handler
import android.os.Looper
import com.farad.entertainment.aramkada.app.BaseApp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

   // single { Moshi.Builder().build() }
    single { Handler(Looper.getMainLooper()) }
    single { androidApplication() as BaseApp }

}