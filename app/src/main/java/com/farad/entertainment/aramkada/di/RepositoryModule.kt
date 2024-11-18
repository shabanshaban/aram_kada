package com.farad.entertainment.aramkada.di


import com.farad.entertainment.aramkada.data.apiService.repository.HomeRepository
import com.farad.entertainment.aramkada.data.apiService.repository.HomeRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.core.module.dsl.bind
val repositoryModule = module {

    singleOf(::HomeRepositoryImpl) { bind<HomeRepository>() }

}