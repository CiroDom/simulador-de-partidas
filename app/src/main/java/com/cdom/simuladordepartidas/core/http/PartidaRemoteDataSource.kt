package com.cdom.simuladordepartidas.core.http

import com.cdom.simuladordepartidas.core.models.Partida
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartidaRemoteDataSource {

    fun findPartidasFromAPI(callback: OurCallbacks) {
        HTTPClient.buildRetrofit()
            .create(PartidasAPI::class.java)
            .getPartidas()
            .enqueue( object : Callback<List<Partida>> {
                override fun onResponse(
                    call: Call<List<Partida>>,
                    response: Response<List<Partida>>
                ) {
                    if (response.isSuccessful) {
                        val partidas = response.body()

                        callback.onSucess(partidas ?: emptyList())
                    } else {
                        val error =
                            if (response.errorBody() != null) {
                                response.errorBody()
                            } else {
                                "Erro desconhecido"
                            }

                        callback.onError("Erro na chamada: $error")
                    }
                }
                override fun onFailure(call: Call<List<Partida>>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                }
            })
    }
}