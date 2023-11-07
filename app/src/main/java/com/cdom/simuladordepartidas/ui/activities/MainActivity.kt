package com.cdom.simuladordepartidas.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdom.simuladordepartidas.core.models.Partida
import com.cdom.simuladordepartidas.core.presenters.MainPresenter
import com.cdom.simuladordepartidas.databinding.ActivityMainBinding
import com.cdom.simuladordepartidas.ui.recy_view.PartidaItem
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupieAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val presenter by lazy {
        MainPresenter(this)
    }

    private val groupieAdapter = GroupieAdapter()

    val swipePartidas by lazy {
        binding.swipePartidas
    }

    val recyView by lazy {
        binding.recyViewPartidas
    }

    var partidasOk = false


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("mainActv", "antes do super")

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        Log.i("mainActv", "depois do setContent")

        setupRecyView()
//        setupRefresh()
        setupFAB()
    }

    private fun setupRecyView() {
        Log.i("mainActv", "setupRecyView")
        recyView.setHasFixedSize(true)
        recyView.layoutManager = LinearLayoutManager(this)

        presenter.findPartidas()

        recyView.adapter = groupieAdapter
    }

//    private fun setupRefresh() {
//        presenter.findPartidas()
//    }

    private fun setupFAB() {
        val fabSimular = binding.fabSimular

        fabSimular.setOnClickListener{ fab ->
            fab.animate().rotationBy(360f).setDuration(500).setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)

                    if (partidasOk) {
                        presenter.newScore()
                    }
                }
            }).start()
        }
    }

    fun showPartidas(response: List<Partida>) {
        Log.i("mainAtcv", "showPartidas")

        val listPartidaItem = response.map { PartidaItem(it) }
        groupieAdapter.addAll(listPartidaItem)
        groupieAdapter.notifyDataSetChanged()
    }

    fun showSnackBar(message: String) {
        Snackbar.make(binding.fabSimular, message, Snackbar.LENGTH_LONG).show()
    }
}
