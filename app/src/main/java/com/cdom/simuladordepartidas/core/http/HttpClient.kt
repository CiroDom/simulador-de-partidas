package com.cdom.simuladordepartidas.core.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {

    private const val URL_BASE = "https://raw.githubusercontent.com/CiroDom/simulador-partidas-api/main/"

    fun criarInstRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}