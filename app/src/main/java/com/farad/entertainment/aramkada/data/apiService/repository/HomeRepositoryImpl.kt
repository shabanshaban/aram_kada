package com.farad.entertainment.aramkada.data.apiService.repository

import com.farad.entertainment.aramkada.data.dataSource.DataSourceRemoteHome

class HomeRepositoryImpl(

    private val dataSourceRemoteHome: DataSourceRemoteHome

) : HomeRepository {
    override suspend fun getCategoryList() = dataSourceRemoteHome.getCategoryList()

    override suspend fun getCategoryItemList(catId: Long) =
        dataSourceRemoteHome.getCategoryItemList(catId)

    override suspend fun getSessionList(itemId: Long) = dataSourceRemoteHome.getSessionList(itemId)


}