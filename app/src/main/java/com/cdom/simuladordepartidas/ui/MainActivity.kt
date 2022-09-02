package com.cdom.simuladordepartidas.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cdom.simuladordepartidas.classesdominio.Partida
import com.cdom.simuladordepartidas.dados.PartidasAPI
import com.cdom.simuladordepartidas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var partidasAdapter: PartidasAdapter
    private lateinit var partidasAPI: PartidasAPI
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupListaPartidas()
//        setupAtualizarPartidas()
//        setupFAB()
    }

    private fun setupHttpClient() {
        partidasAPI = PartidasAPI.criarInstRetrofit()
    }

    private fun setupListaPartidas() {
        val rView = binding.recyViewPartidas
        rView.setHasFixedSize(true)
        rView.layoutManager = LinearLayoutManager(this)

        partidasAPI.getPartidas().enqueue(object : Callback<List<Partida>> {

            override fun onResponse(call: Call<List<Partida>>, response: Response<List<Partida>>) {
                if(response.isSuccessful) {
                    val partidas = response.body()!!
                    partidasAdapter = PartidasAdapter(partidas)
                    rView.adapter = partidasAdapter
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
