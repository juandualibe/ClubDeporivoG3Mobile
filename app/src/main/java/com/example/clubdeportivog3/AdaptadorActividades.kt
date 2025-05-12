package com.example.clubdeportivog3

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorActividades(
    private val listaActividades: List<ActividadDeportiva>,
    private val onAccionActividad: (accion: String, actividad: ActividadDeportiva) -> Unit
) : RecyclerView.Adapter<AdaptadorActividades.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // --Commented out by Inspection (12/5/2025 17:26):val iconoActividad: ImageView = itemView.findViewById(R.id.iconoActividad)
        val textoNombre: TextView = itemView.findViewById(R.id.textoNombre)
        val textoDescripcion: TextView = itemView.findViewById(R.id.textoDescripcion)
        val iconoEditar: ImageView = itemView.findViewById(R.id.iconoEditar)
        val iconoEliminar: ImageView = itemView.findViewById(R.id.iconoEliminar)
        val contenedorTexto: LinearLayout = itemView.findViewById(R.id.contenedorTexto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actividad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actividad = listaActividades[position]
        holder.textoNombre.text = actividad.nombre
        holder.textoDescripcion.text = "${actividad.descripcion}\nCupo: ${actividad.cupoMaximo}"

        // Navegar a ActividadDetailsActivity al hacer clic en el contenedor de texto
        holder.contenedorTexto.setOnClickListener {
            val intent = Intent(holder.itemView.context, ActividadDetailsActivity::class.java).apply {
                putExtra("ACTIVIDAD_ID", actividad.id)
                putExtra("ACTIVIDAD_NOMBRE", actividad.nombre)
                putExtra("ACTIVIDAD_DESCRIPCION", actividad.descripcion)
                putExtra("ACTIVIDAD_NUMERO", actividad.id) // Usar id en lugar de position
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.iconoEditar.setOnClickListener {
            onAccionActividad("editar", actividad)
        }
        holder.iconoEliminar.setOnClickListener {
            onAccionActividad("eliminar", actividad)
        }
    }

    override fun getItemCount(): Int = listaActividades.size
}