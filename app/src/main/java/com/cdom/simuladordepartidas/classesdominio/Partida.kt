package com.cdom.simuladordepartidas.classesdominio

import com.google.gson.annotations.SerializedName

data class Partida(
    @SerializedName("descricao")
    val descricao: String,
    @SerializedName("local")
    val local: Local,
    @SerializedName("da casa")
    val timeCasa: Time,
    @SerializedName("visitante")
    val timeVisitante: Time
)
