package com.cdom.simuladordepartidas.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Partida(
    @SerializedName("descricao")
    val descricao: String,
    @SerializedName("local")
    val local: Local,
    @SerializedName("da casa")
    val timeCasa: Time,
    @SerializedName("visitante")
    val timeVisitante: Time
) : Parcelable
