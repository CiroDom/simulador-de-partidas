package com.cdom.simuladordepartidas.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Local(
    @SerializedName("nome")
    val nome: String,
    @SerializedName("imagem")
    val imagem: String
) : Parcelable