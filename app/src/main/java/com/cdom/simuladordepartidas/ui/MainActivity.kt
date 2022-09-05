package com.cdom.simuladordepartidas.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cdom.simuladordepartidas.classesdominio.Partida
import com.cdom.simuladordepartidas.dados.PartidasAPI
import com.cdom.simuladordepartidas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val rView by lazy {
        binding.recyViewPartidas
    }

    private val swipePartidas by lazy {
        binding.swprfrshPartidas
    }

    private lateinit var partidasAdapter: PartidasAdapter
    private lateinit var partidasAPI: PartidasAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupHttpClient()
        setupListaPartidas()
        setupAtualizarPartidas()
        setupFAB()
    }

    private fun setupHttpClient() {
        partidasAPI = PartidasAPI.criarInstRetrofit()
    }

    private fun setupListaPartidas() {
        rView.setHasFixedSize(true)
        rView.layoutManager = LinearLayoutManager(this)

        buscarPartidasDaAPI()
    }

    private fun setupAtualizarPartidas() {
        swipePartidas.setOnRefreshListener(this::buscarPartidasDaAPI)
    }

    private fun setupFAB() {
        val fabSimular = binding.fabSimular
        fabSimular.setOnClickListener{ botao ->
            botao.animate().rotationBy(360f).setDuration(500).setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    simularNovaPartida()
                }
            }).start()

        }
    }

    private fun simularNovaPartida() {
        for (i in 0 until partidasAdapter.itemCount) {
            val partida = partidasAdapter.partidas[i]

            // random aplicado de maneira diversa a do curso
            partida.timeCasa.placar = (0..partida.timeCasa.estrelas).random()
            partida.timeVisitante.placar = (0..partida.timeVisitante.estrelas).random()

            partidasAdapter.notifyItemChanged(i)
        }
    }

    private fun buscarPartidasDaAPI() {
        partidasAPI.getPartidas().enqueue(object : Callback<List<Partida>> {
            override fun onResponse(call: Call<List<Partida>>, response: Response<List<Partida>>) {
                if (response.isSuccessful) {
                    val partidas = response.body()!!
                    partidasAdapter = PartidasAdapter(partidas)
                    rView.adapter = partidasAdapter
                } else {
                    mensagemErro()
                }
                swipePartidas.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Partida>>, t: Throwable) {
                mensagemErro()
                swipePartidas.isRefreshing = false
            }
        })
    }

    private fun mensagemErro() {
        Snackbar.make(binding.fabSimular, "Erro inesperado!", Snackbar.LENGTH_LONG).show()
    }

}
