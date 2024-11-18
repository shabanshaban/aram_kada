package com.farad.entertainment.aramkada.di


import com.farad.entertainment.aramkada.data.dataSource.DataSourceRemoteHome
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val dataSourceModule = module {


    singleOf(::DataSourceRemoteHome)

}