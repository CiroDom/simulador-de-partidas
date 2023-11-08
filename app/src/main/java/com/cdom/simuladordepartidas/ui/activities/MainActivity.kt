package com.cdom.simuladordepartidas.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdom.simuladordepartidas.core.models.Partida
import com.cdom.simuladordepartidas.core.presenters.MainPresenter
import com.cdom.simuladordepartidas.databinding.ActivityMainBinding
import com.cdom.simuladordepartidas.ui.recy_view.PartidasAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val presenter by lazy {
        MainPresenter(this)
    }

    val swipePartidas by lazy {
        binding.swipePartidas
    }

    val recyView by lazy {
        binding.recyViewPartidas
    }

    private lateinit var partidasAdapter: PartidasAdapter

    var partidasOk = false


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("mainActv", "antes do super")

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.i("mainActv", "depois do setContent")

        recyView.layoutManager = LinearLayoutManager(this)
        presenter.findPartidas()
        setupRefresh()
        setupFAB()
    }

    private fun setupRefresh() {
        swipePartidas.setOnRefreshListener {
            presenter.findPartidas()
        }
    }

    private fun setupFAB() {
        val fabSimular = binding.fabSimular

        fabSimular.setOnClickListener{ fab ->
            fab.animate().rotationBy(360f).setDuration(500).setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)

                    if (partidasOk) {
                        presenter.newScore()
                        partidasAdapter.notifyDataSetChanged()
                        Log.i("mainActv", "chegou na newScore")
                    }
                }
            }).start()
        }
    }

    fun showPartidas(response: List<Partida>) {
        Log.i("mainActv", "showPartidas")
        partidasAdapter = PartidasAdapter(response)
        recyView.adapter = partidasAdapter

        partidasAdapter.notifyDataSetChanged()
    }

    fun showSnackBar(message: String) {
        Snackbar.make(binding.fabSimular, message, Snackbar.LENGTH_LONG).show()
    }
}