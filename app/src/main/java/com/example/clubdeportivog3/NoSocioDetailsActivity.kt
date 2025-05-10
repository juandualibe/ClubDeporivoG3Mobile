package com.example.clubdeportivog3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NoSocioDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_no_socio_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener datos del Intent
        val noSocioNombre = intent.getStringExtra("NO_SOCIO_NOMBRE") ?: "Desconocido"
        val noSocioNumero = intent.getIntExtra("NO_SOCIO_NUMERO", 123)

        // Referencias a los elementos del layout
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val tvSocioNumero = findViewById<TextView>(R.id.tvSocioNumero)
        val tvDetalles = findViewById<TextView>(R.id.tvDetalles)
        val tvEstadoPago = findViewById<TextView>(R.id.tvEstadoPago)
        val btnRegistrarPago = findViewById<Button>(R.id.btnRegistrarPago)
        val btnInscribirActividad = findViewById<Button>(R.id.btnInscribirActividad)
        val llActividades = findViewById<LinearLayout>(R.id.llActividades)

        // Configurar información del no socio
        tvSocioNumero.text = "No Socio N° $noSocioNumero"
        tvDetalles.text = "Nombre: $noSocioNombre\nDNI: 12345678\nCorreo: $noSocioNombre@example.com"

        // Configurar estado de pago (alternar según ejemplo)
        val pagoPendiente = noSocioNumero % 2 == 0 // Números pares: pendiente
        tvEstadoPago.text = if (pagoPendiente) "Pago pendiente" else "Pago realizado"
        tvEstadoPago.setTextColor(
            if (pagoPendiente) getColor(android.R.color.holo_red_dark)
            else getColor(android.R.color.holo_green_dark)
        )

        // Botón Volver
        btnVolver.setOnClickListener {
            val intent = Intent(this, NoSociosActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Botón Registrar Pago
        btnRegistrarPago.setOnClickListener {
            Toast.makeText(this, "Pago registrado para $noSocioNombre", Toast.LENGTH_SHORT).show()
            tvEstadoPago.text = "Pago realizado"
            tvEstadoPago.setTextColor(getColor(android.R.color.holo_green_dark))
        }

        // Botón Inscribir en Actividad
        btnInscribirActividad.setOnClickListener {
            Toast.makeText(this, "Inscribir a $noSocioNombre en actividad", Toast.LENGTH_SHORT).show()
        }

        // Lista ficticia de actividades
        val listaActividades = listOf(
            ActividadDeportiva("Fútbol", "Partidos semanales en cancha sintética"),
            ActividadDeportiva("Natación", "Clases para todas las edades"),
            ActividadDeportiva("Yoga", "Sesiones de relajación y estiramiento"),
            ActividadDeportiva("Tenis", "Canchas disponibles para práctica"),
            ActividadDeportiva("Vóley", "Entrenamiento en playa y gimnasio")
        )

        // Configurar las tarjetas de actividades hardcoded
        val activityViews = listOf(
            findViewById<TextView>(R.id.tvNombreActividad1) to findViewById<Button>(R.id.btnRevocar1),
            findViewById<TextView>(R.id.tvNombreActividad2) to findViewById<Button>(R.id.btnRevocar2),
            findViewById<TextView>(R.id.tvNombreActividad3) to findViewById<Button>(R.id.btnRevocar3),
            findViewById<TextView>(R.id.tvNombreActividad4) to findViewById<Button>(R.id.btnRevocar4),
            findViewById<TextView>(R.id.tvNombreActividad5) to findViewById<Button>(R.id.btnRevocar5)
        )

        activityViews.forEachIndexed { index, (tvNombre, btnRevocar) ->
            if (index < listaActividades.size) {
                val actividad = listaActividades[index]
                tvNombre.text = "${actividad.nombre}\n${actividad.descripcion}"
                btnRevocar.setOnClickListener {
                    Toast.makeText(this, "Revocar inscripción en ${actividad.nombre}", Toast.LENGTH_SHORT).show()
                    tvNombre.text = "Inscripción revocada"
                    btnRevocar.visibility = View.GONE
                }
            } else {
                // Ocultar tarjetas vacías
                tvNombre.visibility = View.GONE
                btnRevocar.visibility = View.GONE
            }
        }
    }
}