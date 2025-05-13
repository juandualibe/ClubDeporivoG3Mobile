package com.example.clubdeportivog3.data

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clubdeportivog3.R
import com.example.clubdeportivog3.activities.DeletedNoSocioActivity
import com.example.clubdeportivog3.activities.NoSocioDetailsActivity

class AdaptadorNoSocios(
    private val listaNoSocios: List<String>,
    private val onAccionNoSocio: (accion: String, noSocio: String) -> Unit
) : RecyclerView.Adapter<AdaptadorNoSocios.ViewHolder>() {

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
        val noSocio = listaNoSocios[position]
        holder.textoSocio.text = noSocio

        // Abrir NoSocioDetailsActivity al hacer clic en el nombre
        holder.textoSocio.setOnClickListener {
            val intent = Intent(holder.itemView.context, NoSocioDetailsActivity::class.java).apply {
                putExtra("NO_SOCIO_NOMBRE", noSocio)
                putExtra("NO_SOCIO_NUMERO", position + 100) // Número ficticio
            }
            holder.itemView.context.startActivity(intent)
        }

        // Configurar clics en los íconos
        holder.iconoEditar.setOnClickListener {
            onAccionNoSocio("editar", noSocio)
        }
        holder.iconoEliminar.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setMessage("¿Está seguro que desea eliminar este no socio?")
                .setNegativeButton("No") { _, _ ->
                    // No hacer nada, se queda en NoSocioListActivity
                }
                .setPositiveButton("Sí") { _, _ ->
                    // Navegar a DeletedNoSocioActivity
                    val intent = Intent(holder.itemView.context, DeletedNoSocioActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }
                .setCancelable(true)
                .show()
        }
    }

    override fun getItemCount(): Int = listaNoSocios.size
}