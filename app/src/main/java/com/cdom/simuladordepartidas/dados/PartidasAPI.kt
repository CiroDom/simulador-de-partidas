package com.cdom.simuladordepartidas.dados

import com.cdom.simuladordepartidas.classesdominio.Partida
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface PartidasAPI {

    @GET("partidas.json")
    fun getPartidas(): Call<List<Partida>>

    companion object {

        //link raw sem e com (lembrar de tirar o "partidas.json" e a barra (/) no final
        //raw: https://raw.githubusercontent.com/CiroDom/simulador-partidas-api/main/partidas.json
        //normal: https://cirodom.github.io/simulador-partidas-api/partidas.json
        private const val urlBase = "https://raw.githubusercontent.com/CiroDom/simulador-partidas-api/main" + "/"

        fun criarInstRetrofit(): PartidasAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create() //PartidasAPI::class.java com e sem
        }
    }
}