package com.farad.entertainment.aramkada.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Keep
@Parcelize
data class CategoryModel(
    @SerializedName( "id")
    val id: Int,
    @SerializedName( "titile")
    val title: String
) : Parcelable