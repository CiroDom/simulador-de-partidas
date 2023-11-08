package com.cdom.simuladordepartidas.ui.recy_view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cdom.simuladordepartidas.core.models.Partida
import com.cdom.simuladordepartidas.databinding.PartidaItemBinding
import com.cdom.simuladordepartidas.ui.activities.DetalhesActivity

class PartidasAdapter(private val partidas: List<Partida>) : RecyclerView.Adapter<PartidasAdapter.VHolder>() {

    class VHolder(val binding: PartidaItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layInf = LayoutInflater.from(parent.context)
        val binding = PartidaItemBinding.inflate(layInf, parent, false)

        return VHolder(binding)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        val contexto = holder.itemView.context
        val partida = partidas[position]

        val hbinding = holder.binding

        // da casa
        hbinding.tvNomeCasa.text = partida.timeCasa.nome
        Glide.with(contexto).load(partida.timeCasa.imagem).circleCrop().into(hbinding.imCasa) //igual (=) sem e com
        hbinding.tvPlacarCasa.text = partida.timeCasa.placar.toString()
        // visitante
        hbinding.tvNomeVisitante.text = partida.timeVisitante.nome
        Glide.with(contexto).load(partida.timeVisitante.imagem).circleCrop().into(hbinding.imVisitante) //igual (=) sem e com
        hbinding.tvPlacarVisitante.text = partida.timeVisitante.placar.toString()

        holder.itemView.setOnClickListener{
            val intent = Intent(contexto, DetalhesActivity::class.java)
            intent.putExtra(DetalhesActivity.Extras.PARTIDA, partida)
            contexto.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return partidas.size
    }

}