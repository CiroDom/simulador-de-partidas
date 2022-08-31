package com.cdom.simuladordepartidas.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cdom.simuladordepartidas.classesdominio.Partida
import com.cdom.simuladordepartidas.dados.PartidasAPI
import com.cdom.simuladordepartidas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var partidasAPI: PartidasAPI
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupListaPartidas()
        setupAtualizarPartidas()
        setupFAB()
    }

    private fun setupHttpClient() {
        PartidasAPI.criarInstRetrofit()
    }

    private fun setupListaPartidas() {
        //SOB TESTE E EM DÚVIDA
        partidasAPI.getPartidas().enqueue(object : Callback, retrofit2.Callback<List<Partida>> {
            override fun onResponse(call: Call<List<Partida>>, response: Response<List<Partida>>) {
                if (response.isSuccessful){
                    val partidas = response.body() //ver diferença de sem e com o body()
                }
                else {
                    mensagemErro()
                }
            }

            override fun onFailure(call: Call<List<Partida>>, t: Throwable) {
                mensagemErro()
            }

        })
    }

    private fun setupAtualizarPartidas() {
        TODO("Not yet implemented")
    }

    private fun setupFAB() {
        TODO("Not yet implemented")
    }

    private fun mensagemErro() {
        Snackbar.make(binding.fabSimular, "Erro inesperado!", Snackbar.LENGTH_LONG).show()
    }
}
