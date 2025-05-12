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

class SocioDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_socio_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener datos del Intent
        val socioNombre = intent.getStringExtra("SOCIO_NOMBRE") ?: "Desconocido"
        val socioNumero = intent.getIntExtra("SOCIO_NUMERO", 123)
        val origen = intent.getStringExtra("ORIGEN") ?: "SocioListActivity"

        // Referencias a los elementos del layout
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val tvSocioNumero = findViewById<TextView>(R.id.tvSocioNumero)
        val tvDetalles = findViewById<TextView>(R.id.tvDetalles)
        val tvCuota = findViewById<TextView>(R.id.tvCuota)
        val tvEstadoPago = findViewById<TextView>(R.id.tvEstadoPago)
        val btnRegistrarPago = findViewById<Button>(R.id.btnRegistrarPago)
        val btnInscribirActividad = findViewById<Button>(R.id.btnInscribirActividad)
        val llActividades = findViewById<LinearLayout>(R.id.llActividades)

        // Configurar información del socio
        tvSocioNumero.text = "Socio N° $socioNumero"
        tvDetalles.text = "Nombre: $socioNombre\nDNI: 12345678\nCorreo: $socioNombre@example.com"
        tvCuota.text = "$ 15000"

        // Configurar estado de pago
        val pagoPendiente = socioNumero % 2 == 0
        tvEstadoPago.text = if (pagoPendiente) "Pago pendiente" else "Pago realizado"
        tvEstadoPago.setTextColor(
            if (pagoPendiente) getColor(android.R.color.holo_red_dark)
            else getColor(android.R.color.holo_green_dark)
        )

        // Botón Volver
        btnVolver.setOnClickListener {
            val intent = Intent(
                this,
                if (origen == "ExpirationActivity") ExpirationActivity::class.java else SocioListActivity::class.java
            )
            startActivity(intent)
            finish()
        }

        // Botón Registrar Pago
        btnRegistrarPago.setOnClickListener {
            Toast.makeText(this, "Pago registrado para $socioNombre", Toast.LENGTH_SHORT).show()
            tvEstadoPago.text = "Pago realizado"
            tvEstadoPago.setTextColor(getColor(android.R.color.holo_green_dark))
        }

        // Botón Inscribir en Actividad
        btnInscribirActividad.setOnClickListener {
            val intent = Intent(this, RegisterInActivityActivity::class.java).apply {
                putExtra("socio_id", socioNumero)
                putExtra("socio_nombre", socioNombre)
            }
            startActivity(intent)
        }

        // Lista ficticia de actividades
        val listaActividades = listOf(
            ActividadDeportiva("Fútbol", "Partidos semanales en cancha sintética"),
            ActividadDeportiva("Natación", "Clases para todas las edades"),
            ActividadDeportiva("Yoga", "Sesiones de relajación y estiramiento"),
            ActividadDeportiva("Tenis", "Canchas disponibles para práctica"),
            ActividadDeportiva("Vóley", "Entrenamiento en playa y gimnasio")
        )

        // Configurar las tarjetas de actividades
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
                tvNombre.visibility = View.GONE
                btnRevocar.visibility = View.GONE
            }
        }
    }
}