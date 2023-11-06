package com.cdom.simuladordepartidas.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Time(
    @SerializedName("nome")
    val nome: String,
    @SerializedName("forca")
    val estrelas: Int,
    @SerializedName("imagem")
    val imagem: String,
    var placar: Int
) : Parcelable
