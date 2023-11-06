package com.cdom.simuladordepartidas.core.http

import com.cdom.simuladordepartidas.core.models.Partida
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface PartidasAPI {

    @GET("partidas.json")
    fun getPartidas(): Call<List<Partida>>

}