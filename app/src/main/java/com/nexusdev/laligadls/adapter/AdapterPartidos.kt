package com.nexusdev.laligadls.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexusdev.laligadls.R
import com.nexusdev.laligadls.data.Partidos
import com.nexusdev.laligadls.databinding.PartidosItemListBinding

class AdapterPartidos(private val partidosList: List<Partidos>) :
    RecyclerView.Adapter<AdapterPartidos.PartidosViewHolder>() {
    private val listaTablaFiltrada = partidosList.toMutableList()
    var onItemClick: ((Partidos) -> Unit)? = null

    class PartidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun render(partido: Partidos) {
            val binding = PartidosItemListBinding.bind(itemView)

            binding.teamOne.text = partido.equipoUno
            binding.goalsOne.text = partido.golesUno
            binding.teamTwo.text = partido.equipoDos
            binding.goalsTwo.text = partido.golesDos
            binding.fecha.text = partido.fecha
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterPartidos.PartidosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PartidosViewHolder(
            layoutInflater.inflate(
                R.layout.partidos_item_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: AdapterPartidos.PartidosViewHolder,
        position: Int
    ) {
        val item = listaTablaFiltrada[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int = listaTablaFiltrada.size


}