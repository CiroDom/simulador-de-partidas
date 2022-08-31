package com.cdom.simuladordepartidas.classesdominio

import com.google.gson.annotations.SerializedName

data class Local(
    @SerializedName("nome")
    val nome: String,
    @SerializedName("imagem")
    val imagem: String
)