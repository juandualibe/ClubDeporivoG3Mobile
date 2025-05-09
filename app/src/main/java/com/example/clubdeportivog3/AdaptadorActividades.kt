package com.example.clubdeportivog3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorActividades(
    private val listaActividades: List<ActividadDeportiva>,
    private val onAccionActividad: (accion: String, actividad: ActividadDeportiva) -> Unit
) : RecyclerView.Adapter<AdaptadorActividades.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconoActividad: ImageView = itemView.findViewById(R.id.iconoActividad)
        val textoNombre: TextView = itemView.findViewById(R.id.textoNombre)
        val textoDescripcion: TextView = itemView.findViewById(R.id.textoDescripcion)
        val iconoEditar: ImageView = itemView.findViewById(R.id.iconoEditar)
        val iconoEliminar: ImageView = itemView.findViewById(R.id.iconoEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actividad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actividad = listaActividades[position]
        holder.textoNombre.text = actividad.nombre
        holder.textoDescripcion.text = actividad.descripcion

        holder.iconoEditar.setOnClickListener {
            onAccionActividad("editar", actividad)
        }
        holder.iconoEliminar.setOnClickListener {
            onAccionActividad("eliminar", actividad)
        }
    }

    override fun getItemCount(): Int = listaActividades.size
}