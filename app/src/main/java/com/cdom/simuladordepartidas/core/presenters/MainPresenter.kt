package com.cdom.simuladordepartidas.core.presenters

import com.cdom.simuladordepartidas.core.http.OurCallbacks
import com.cdom.simuladordepartidas.core.http.PartidaRemoteDataSource
import com.cdom.simuladordepartidas.core.models.Partida
import com.cdom.simuladordepartidas.ui.activities.MainActivity

class MainPresenter(
    private val view: MainActivity,
    private val dataSource: PartidaRemoteDataSource = PartidaRemoteDataSource()
) : OurCallbacks {

    override fun onSucess(response: List<Partida>) {
        view.showPartidas(response)
    }

    fun buscarPartidas() {
        dataSource.buscarPartidasDaAPI(this)
    }

}