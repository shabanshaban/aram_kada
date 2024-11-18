package com.farad.entertainment.aramkada.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class SessionModel(
    @SerializedName(  "free")
    val free: Int,
    @SerializedName(   "id")
    val id: Int,
    @SerializedName( "room_id")
    val roomId: Int,
    @SerializedName( "session")
    val session: String,
    @SerializedName( "time")
    val time: String,
    @SerializedName( "url")
    val url: String
)