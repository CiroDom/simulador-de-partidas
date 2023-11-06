package com.cdom.simuladordepartidas.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdom.simuladordepartidas.core.models.Partida
import com.cdom.simuladordepartidas.core.presenters.MainPresenter
import com.cdom.simuladordepartidas.databinding.ActivityMainBinding
import com.cdom.simuladordepartidas.ui.recy_view.PartidaItem
import com.xwray.groupie.GroupieAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val presenter by lazy {
        MainPresenter(this)
    }

    private val groupieAdapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(binding.root)

        val recyView = binding.recyViewPartidas
        recyView.layoutManager = LinearLayoutManager(this)

        presenter.buscarPartidas()

        recyView.adapter = groupieAdapter

    }

    fun showPartidas(partidas: List<Partida>) {
        val partidaItens = partidas.map { PartidaItem(it) }
        groupieAdapter.addAll(partidaItens)
        groupieAdapter.notifyDataSetChanged()
    }


}
