package com.farad.entertainment.aramkada.data.dataSource

import com.farad.entertainment.aramkada.data.apiService.api.HomeApi

class DataSourceRemoteHome(private val homeApi: HomeApi) {


    suspend fun getCategoryList() = homeApi.getCategoryList()

    suspend fun getCategoryItemList(catId: Long) = homeApi.getCategoryItemList(catId)

    suspend fun getSessionList(itemId: Long) = homeApi.getSessionList(itemId)
}