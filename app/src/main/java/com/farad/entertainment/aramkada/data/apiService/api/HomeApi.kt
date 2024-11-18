package com.farad.entertainment.aramkada.data.apiService.api

import com.farad.entertainment.aramkada.data.model.CategoryItemModel
import com.farad.entertainment.aramkada.data.model.CategoryModel
import com.farad.entertainment.aramkada.data.model.SessionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("meditation/meditation_cat.php")
    suspend fun getCategoryList(): Response<List<CategoryModel>>

    @GET("meditation/meditation_item.php")
    suspend fun getCategoryItemList(@Query("cat_id") catId: Long): Response<List<CategoryItemModel>>

    @GET("meditation/meditation_session.php")
    suspend fun getSessionList(@Query("item_id") itemId: Long): Response<List<SessionModel>>
}