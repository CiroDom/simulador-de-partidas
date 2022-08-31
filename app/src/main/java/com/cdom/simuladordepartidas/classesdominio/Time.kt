package com.cdom.simuladordepartidas.classesdominio

import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("nome")
    val nome: String,
    @SerializedName("forca")
    val forca: Int,
    @SerializedName("imagem")
    val imagem: String
)