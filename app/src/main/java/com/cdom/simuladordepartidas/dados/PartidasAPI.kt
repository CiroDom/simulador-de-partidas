package com.cdom.simuladordepartidas.dados

import com.cdom.simuladordepartidas.classesdominio.Partida
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PartidasAPI {

    @GET("partidas.json")
    fun getPartidas() : Call<List<Partida>>

    companion object {

        var urlBase = //lembrar que tá com o raw, não o outro link
            "https://raw.githubusercontent.com/CiroDom/simulador-partidas-api/main/partidas.json"

        fun criarInstRetrofit() : PartidasAPI {
            var retrofit = Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PartidasAPI::class.java) //lembrar de testar sem o PartidasAPI::class.java
        }
    }
}