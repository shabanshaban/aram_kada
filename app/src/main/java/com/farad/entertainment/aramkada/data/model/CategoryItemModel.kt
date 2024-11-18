package com.farad.entertainment.aramkada.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class CategoryItemModel(
    @SerializedName( "id")
    val id: Int,
    @SerializedName( "image")
    val image: String,
    @SerializedName("image2")
    val image2: String,
    @SerializedName( "sessions")
    val sessions: String,
    @SerializedName( "titile")
    val title: String
) : Parcelable