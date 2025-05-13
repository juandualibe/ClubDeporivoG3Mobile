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
import com.example.clubdeportivog3.activities.DeletedSocioActivity
import com.example.clubdeportivog3.activities.SocioDetailsActivity

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

        // Abrir SocioDetailsActivity al hacer clic en el nombre
        holder.textoSocio.setOnClickListener {
            val intent = Intent(holder.itemView.context, SocioDetailsActivity::class.java).apply {
                putExtra("SOCIO_NOMBRE", socio)
                putExtra("SOCIO_NUMERO", position + 100) // Número ficticio
            }
            holder.itemView.context.startActivity(intent)
        }

        // Configurar clics en los íconos
        holder.iconoEditar.setOnClickListener {
            onAccionSocio("editar", socio)
        }
        holder.iconoEliminar.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setMessage("¿Está seguro que desea eliminar este socio?")
                .setNegativeButton("No") { _, _ ->
                    // No hacer nada, se queda en SocioListActivity
                }
                .setPositiveButton("Sí") { _, _ ->
                    // Navegar a DeletedSocioActivity
                    val intent = Intent(holder.itemView.context, DeletedSocioActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }
                .setCancelable(true)
                .show()
        }
    }

    override fun getItemCount(): Int = listaSocios.size
}