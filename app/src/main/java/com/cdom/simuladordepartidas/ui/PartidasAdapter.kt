package com.cdom.simuladordepartidas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cdom.simuladordepartidas.classesdominio.Partida
import com.cdom.simuladordepartidas.databinding.PartidaItemBinding

class PartidasAdapter (private val partidas: List<Partida>) :
    RecyclerView.Adapter<PartidasAdapter.VHolder>() {

    class VHolder(val binding: PartidaItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val layInf = LayoutInflater.from(parent.context)
        val binding = PartidaItemBinding.inflate(layInf, parent, false)

        return VHolder(binding)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        val partida = partidas[position]

        val hbinding = holder.binding
        hbinding.tvNomeCasa.text = partida.timeCasa.nome
        hbinding.tvNomeVisitante.text = partida.timeVisitante.nome
    }

    override fun getItemCount(): Int {
        return partidas.size
    }


}
