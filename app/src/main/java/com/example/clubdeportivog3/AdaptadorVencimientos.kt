package com.example.clubdeportivog3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorVencimientos(
    private val listaVencimientos: List<String>,
    private val onAccionVencimiento: (accion: String, socio: String) -> Unit
) : RecyclerView.Adapter<AdaptadorVencimientos.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconoSocio: ImageView = itemView.findViewById(R.id.iconoSocio)
        val textoSocio: TextView = itemView.findViewById(R.id.textoSocio)
        val iconoPago: TextView = itemView.findViewById(R.id.iconoPago) // Cambiado de ImageView a TextView
        val iconoEliminar: ImageView = itemView.findViewById(R.id.iconoEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vencimiento, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val socio = listaVencimientos[position]
        holder.textoSocio.text = socio

        // Configurar clics en los íconos
        holder.iconoPago.setOnClickListener {
            onAccionVencimiento("pago", socio)
        }
        holder.iconoEliminar.setOnClickListener {
            onAccionVencimiento("eliminar", socio)
        }
    }

    override fun getItemCount(): Int = listaVencimientos.size
}