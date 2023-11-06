package com.cdom.simuladordepartidas.ui.recy_view

import android.content.Intent
import com.bumptech.glide.Glide
import com.cdom.simuladordepartidas.R
import com.cdom.simuladordepartidas.core.models.Partida
import com.cdom.simuladordepartidas.databinding.PartidaItemBinding
import com.cdom.simuladordepartidas.ui.activities.DetalhesActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


class PartidaItem(private val partida: Partida) : Item<PartidaItem.PartidaVHolder>() {

    inner class PartidaVHolder(val binding: PartidaItemBinding) : GroupieViewHolder(binding.root)

    override fun bind(holder: PartidaVHolder, position: Int) {
        val context = holder.itemView.context
        val holderBinding = holder.binding

        holderBinding.tvNomeCasa
        holderBinding.tvPlacarCasa
        Glide.with(context).load(partida.timeCasa.imagem).circleCrop().into(holderBinding.imCasa)

        holderBinding.tvNomeVisitante
        holderBinding.tvPlacarVisitante
        Glide.with(context).load(partida.timeVisitante.imagem).circleCrop().into(holderBinding.imVisitante)

        holder.itemView.setOnClickListener {
            TODO("Intent")
        }

    }

    override fun getLayout(): Int {
        return R.layout.partida_item
    }


}