package com.farad.entertainment.aramkada.data.apiService.repository

import com.farad.entertainment.aramkada.data.model.CategoryItemModel
import com.farad.entertainment.aramkada.data.model.CategoryModel
import com.farad.entertainment.aramkada.data.model.SessionModel
import retrofit2.Response

interface HomeRepository {

    suspend fun getCategoryList(): Response<List<CategoryModel>>

    suspend fun getCategoryItemList(catId: Long): Response<List<CategoryItemModel>>

    suspend fun getSessionList(itemId: Long): Response<List<SessionModel>>
}