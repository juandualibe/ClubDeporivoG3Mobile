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

class NoSociosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_no_socios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar RecyclerView
        val recyclerViewNoSocios = findViewById<RecyclerView>(R.id.recyclerViewNoSocios)
        recyclerViewNoSocios.layoutManager = LinearLayoutManager(this)

        // Lista de no socios ficticia
        val listaNoSocios = listOf(
            "Ana Martínez",
            "Pedro Sánchez",
            "Lucía Fernández",
            "Diego Torres",
            "Sofía Ramírez",
            "Martín López"
        )

        // Configurar adaptador (reutilizamos AdaptadorSocios)
        val adaptadorNoSocios = AdaptadorSocios(listaNoSocios) { accion, noSocio ->
            when (accion) {
                "editar" -> Toast.makeText(this, "Editar: $noSocio", Toast.LENGTH_SHORT).show()
                "eliminar" -> Toast.makeText(this, "Eliminar: $noSocio", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerViewNoSocios.adapter = adaptadorNoSocios

        // Botón Agregar no socio
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            Toast.makeText(this, "Agregar nuevo no socio", Toast.LENGTH_SHORT).show()
        }

        // Botón Volver
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Cierra NoSociosActivity
        }
    }
}