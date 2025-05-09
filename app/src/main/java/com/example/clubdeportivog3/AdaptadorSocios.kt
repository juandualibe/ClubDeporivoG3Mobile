package com.example.clubdeportivog3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorSocios(
    private val listaSocios: List<String>,
    private val onAccionSocio: (accion: String, socio: String) -> Unit
) : RecyclerView.Adapter<AdaptadorSocios.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconoSocio: ImageView = itemView.findViewById(R.id.iconoSocio)
        val textoSocio: TextView = itemView.findViewById(R.id.textoSocio)
        val iconoEditar: ImageView = itemView.findViewById(R.id.iconoEditar)
        val iconoEliminar: ImageView = itemView.findViewById(R.id.iconoEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_socio, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val socio = listaSocios[position]
        holder.textoSocio.text = socio

        // Configurar clics en los íconos
        holder.iconoEditar.setOnClickListener {
            onAccionSocio("editar", socio)
        }
        holder.iconoEliminar.setOnClickListener {
            onAccionSocio("eliminar", socio)
        }
    }

    override fun getItemCount(): Int = listaSocios.size
}