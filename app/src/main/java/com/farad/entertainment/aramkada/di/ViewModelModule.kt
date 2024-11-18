package com.farad.entertainment.aramkada.di

import com.farad.entertainment.aramkada.ui.vm.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::HomeViewModel)


}