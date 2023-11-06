package com.cdom.simuladordepartidas.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.cdom.simuladordepartidas.core.models.Partida
import com.cdom.simuladordepartidas.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesBinding

    object Extras {
        const val PARTIDA = "EXTRA_PARTIDA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        carregarPartidaDoExtra()
    }

    private fun carregarPartidaDoExtra() {
        intent?.extras?.getParcelable<Partida>(Extras.PARTIDA)?.let {

            // Local
            Glide.with(this).load(it.local.imagem).into(binding.imLocal)
            supportActionBar?.title = it.local.nome
            binding.tvDescricaoDetalhes.text = it.descricao

            // Da casa
            Glide.with(this).load(it.timeCasa.imagem).into(binding.imCasaDetalhes)
            binding.tvCasaNomeDetalhes.text = it.timeCasa.nome
            binding.tvCasaPlacarDetalhes.text = it.timeCasa.placar.toString()
            binding.ratbarCasaEstrelas.rating = it.timeCasa.estrelas.toFloat()
            // Visitante
            Glide.with(this).load(it.timeVisitante.imagem).into(binding.imVisitanteDetalhes)
            binding.tvVisitanteNomeDetalhes.text = it.timeVisitante.nome
            binding.tvVisitantePlacarDetalhes.text = it.timeVisitante.placar.toString()
            binding.ratbarVisitanteEstrelas.rating = it.timeVisitante.estrelas.toFloat()
        }
    }
}