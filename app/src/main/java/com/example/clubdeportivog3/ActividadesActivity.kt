package com.example.clubdeportivog3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActividadesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividades)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar RecyclerView
        val recyclerViewActividades = findViewById<RecyclerView>(R.id.recyclerViewActividades)
        recyclerViewActividades.layoutManager = LinearLayoutManager(this)

        // Lista de actividades ficticia con descripciones
        val listaActividades = listOf(
            ActividadDeportiva("Fútbol", "Partidos semanales en cancha sintética"),
            ActividadDeportiva("Básquet", "Entrenamiento y torneos locales"),
            ActividadDeportiva("Natación", "Clases para todas las edades"),
            ActividadDeportiva("Yoga", "Sesiones de relajación y estiramiento"),
            ActividadDeportiva("Tenis", "Canchas disponibles para práctica"),
            ActividadDeportiva("Vóley", "Entrenamiento en playa y gimnasio")
        )

        // Configurar adaptador
        val adaptadorActividades = AdaptadorActividades(listaActividades) { accion, actividad: ActividadDeportiva ->
            when (accion) {
                "editar" -> Toast.makeText(this, "Editar: ${actividad.nombre}", Toast.LENGTH_SHORT).show()
                "eliminar" -> Toast.makeText(this, "Eliminar: ${actividad.nombre}", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerViewActividades.adapter = adaptadorActividades

        // Botón Agregar actividad
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            Toast.makeText(this, "Agregar nueva actividad", Toast.LENGTH_SHORT).show()
        }

        // Botón Volver
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Cierra ActividadesActivity
        }
    }
}