package com.cdom.simuladordepartidas.core.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPClient {

    private const val URL_BASE = "https://raw.githubusercontent.com/CiroDom/simulador-partidas-api/main/"

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}