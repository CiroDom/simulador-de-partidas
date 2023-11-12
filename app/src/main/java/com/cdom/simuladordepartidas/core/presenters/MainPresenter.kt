package com.cdom.simuladordepartidas.core.presenters

import android.util.Log
import com.cdom.simuladordepartidas.core.http.OurCallbacks
import com.cdom.simuladordepartidas.core.http.PartidaRemoteDataSource
import com.cdom.simuladordepartidas.core.models.Partida
import com.cdom.simuladordepartidas.core.singleton.PlacarAntigo
import com.cdom.simuladordepartidas.ui.activities.MainActivity

class MainPresenter(
    private val view: MainActivity,
    private val dataSource: PartidaRemoteDataSource = PartidaRemoteDataSource()
) : OurCallbacks {

    private lateinit var partidas: List<Partida>

    override fun onSucess(response: List<Partida>) {
        Log.i("Presenter", "chegou no onSucess")
        view.swipePartidas.isRefreshing = false
        view.partidasOk = true
        partidas = response
        view.showPartidas(partidas)
        Log.i("Presenter", "chegou no showPartidas")
    }

    override fun onError(response: String) {
        Log.i("Presenter", "chegou no onError")
        view.swipePartidas.isRefreshing = false
        view.partidasOk = false
        view.showSnackBar(response)
    }

    fun findPartidas() {
        Log.i("Presenter", "findPartidas")
        dataSource.findPartidasFromAPI(this)
        PlacarAntigo.salvas = false
    }

    fun newScore() {
        fun randomScore(stars: Int) : Int {
            return (0..stars).random()
        }

        with(PlacarAntigo) {
            salvas = true
            placarCasa.clear()
            placarVisit.clear()
        }

        partidas.forEach { partida ->
            val placarCasa = randomScore(partida.timeCasa.estrelas)
            partida.timeCasa.placar = placarCasa
            PlacarAntigo.placarCasa.add(placarCasa)

            val placarVisit = randomScore(partida.timeVisitante.estrelas)
            partida.timeVisitante.placar = placarVisit
            PlacarAntigo.placarVisit.add(placarVisit)
        }

    }

    fun oldScore() {
        partidas.forEachIndexed { index, partida ->
            partida.timeCasa.placar = PlacarAntigo.placarCasa[index]
            partida.timeVisitante.placar = PlacarAntigo.placarVisit[index]
        }
    }

}