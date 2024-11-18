package com.farad.entertainment.aramkada.di


import com.farad.entertainment.aramkada.data.apiService.api.HomeApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create


val restModule = module {
    single<HomeApi> { (get<Retrofit>()).create() }

}