package com.farad.entertainment.aramkada.di

import com.farad.entertainment.aramkada.utils.Constants
import com.farad.entertainment.aramkada.app.BaseApp
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {


    single {
        createRetrofit(
            get(named("default")),
            Constants.BASE_URL,

        )
    }
    single(named("default")) {
        val cacheSize = (50 * 1024 * 1024).toLong() // 50 MB
        val cache = Cache(androidApplication().cacheDir, cacheSize)
        val okhttpBuilder = OkHttpClient.Builder()
        okhttpBuilder
            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)


        okhttpBuilder.build()
    }

}


fun createRetrofit(okHttpClient: OkHttpClient, url: String ): Retrofit {

    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setStrictness(Strictness.LENIENT).create()))
        .build()
}




