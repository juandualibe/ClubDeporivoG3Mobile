package com.example.clubdeportivog3

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActividadDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener datos del Intent
        val actividadNombre = intent.getStringExtra("ACTIVIDAD_NOMBRE") ?: "Desconocida"
        val actividadDescripcion = intent.getStringExtra("ACTIVIDAD_DESCRIPCION") ?: "Sin descripción"
        val actividadNumero = intent.getIntExtra("ACTIVIDAD_NUMERO", 1)

        // Referencias a los elementos del layout
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val tvActividadNumero = findViewById<TextView>(R.id.tvActividadNumero)
        val tvDetalles = findViewById<TextView>(R.id.tvDetalles)
        val tvInscriptos = findViewById<TextView>(R.id.tvNombreInscripto5)
        val llInscriptos = findViewById<LinearLayout>(R.id.llInscriptos)

        // Configurar información de la actividad
        tvActividadNumero.text = "Actividad N° $actividadNumero"
        tvDetalles.text = "Nombre: $actividadNombre\nDescripción: $actividadDescripcion"

        // Configurar capacidad (ficticia)
        val inscriptosActual = 5 // Valor ficticio
        val inscriptosTotal = 10 // Valor ficticio
        tvInscriptos.text = "Lista de inscriptos ($inscriptosActual/$inscriptosTotal)"

        // Lista ficticia de inscriptos (mezcla de socios y no socios)
        val listaInscriptos = listOf(
            "Juan Pérez", // Socio
            "María Gómez", // Socio
            "Ana López", // No socio
            "Carlos Martínez", // Socio
            "Sofía Ramírez" // No socio
        )

        // Configurar la lista de inscriptos
        val inscriptoViews = listOf(
            findViewById<TextView>(R.id.tvNombreInscripto1) to findViewById<Button>(R.id.btnRevocar1),
            findViewById<TextView>(R.id.tvNombreInscripto2) to findViewById<Button>(R.id.btnRevocar2),
            findViewById<TextView>(R.id.tvNombreInscripto3) to findViewById<Button>(R.id.btnRevocar3),
            findViewById<TextView>(R.id.tvNombreInscripto4) to findViewById<Button>(R.id.btnRevocar4),
            findViewById<TextView>(R.id.tvNombreInscripto5) to findViewById<Button>(R.id.btnRevocar5)
        )

        inscriptoViews.forEachIndexed { index, (tvNombre, btnRevocar) ->
            if (index < listaInscriptos.size) {
                val inscripto = listaInscriptos[index]
                tvNombre.text = inscripto
                btnRevocar.setOnClickListener {
                    AlertDialog.Builder(this)
                        .setMessage("¿Está seguro que desea revocar la inscripción de este cliente?")
                        .setNegativeButton("No") { _, _ ->
                            // No hacer nada, permanecer en ActividadDetailsActivity
                        }
                        .setPositiveButton("Sí") { _, _ ->
                            // Actualizar UI
                            tvNombre.text = "Inscripción revocada"
                            btnRevocar.visibility = View.GONE

                            // Navegar a DeletedInscriptionActivity
                            val intent = Intent(this, DeletedInscriptionActivity::class.java).apply {
                                putExtra("ORIGEN", "ActividadDetailsActivity")
                            }
                            startActivity(intent)
                            finish() // Cierra ActividadDetailsActivity
                        }
                        .setCancelable(true)
                        .show()
                }
            } else {
                tvNombre.visibility = View.GONE
                btnRevocar.visibility = View.GONE
            }
        }

        // Botón Volver
        btnVolver.setOnClickListener {
            val intent = Intent(this, ActividadesListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}