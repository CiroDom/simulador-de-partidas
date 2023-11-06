package com.cdom.simuladordepartidas.core.http

import com.cdom.simuladordepartidas.core.models.Partida

interface OurCallbacks {

    fun onSucess(response: List<Partida>)

    fun onError(response: String)

}