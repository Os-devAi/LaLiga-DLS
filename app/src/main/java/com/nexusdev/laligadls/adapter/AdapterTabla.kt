package com.nexusdev.laligadls.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nexusdev.laligadls.R
import com.nexusdev.laligadls.data.Equipos
import com.nexusdev.laligadls.databinding.TeamItemViewBinding
import java.util.Locale

class AdapterTabla(private val equiposList: List<Equipos>) :
    RecyclerView.Adapter<AdapterTabla.TablaViewHolder>() {

    private val listaTablaFiltrada = equiposList.toMutableList()
    var onItemClick: ((Equipos) -> Unit)? = null

    class TablaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun render(equip: Equipos) {
            val binding = TeamItemViewBinding.bind(itemView)

            binding.equipo.text = equip.abrev
            binding.ptJ.text = equip.partidos
            binding.ganados.text = equip.ganados
            binding.empatados.text = equip.empates
            binding.perdidos.text = equip.perdidos
            binding.puntos.text = equip.puntos
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TablaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TablaViewHolder(layoutInflater.inflate(R.layout.team_item_view, parent, false))
    }

    override fun getItemCount(): Int = listaTablaFiltrada.size

    override fun onBindViewHolder(holder: TablaViewHolder, position: Int) {
        val item = listaTablaFiltrada[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    fun filtrar(query: String) {
        val textoFiltrado = query.lowercase(Locale.getDefault())
        listaTablaFiltrada.clear()
        if (textoFiltrado.isEmpty()) {
            listaTablaFiltrada.addAll(equiposList)
        } else {
            listaTablaFiltrada.addAll(equiposList.filter {
                it.nombre?.lowercase(Locale.getDefault())?.contains(textoFiltrado) == true ||
                        it.abrev?.lowercase(Locale.getDefault())
                            ?.contains(textoFiltrado) == true
            })
        }
        notifyDataSetChanged()
    }
}