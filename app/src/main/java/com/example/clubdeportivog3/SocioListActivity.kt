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

class SocioListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_socio_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar RecyclerView
        val recyclerViewSocios = findViewById<RecyclerView>(R.id.recyclerViewSocios)
        recyclerViewSocios.layoutManager = LinearLayoutManager(this)

        // Lista de socios ficticia
        val listaSocios = listOf(
            "Juan Pérez",
            "María Gómez",
            "Carlos López",
            "Ana Martínez",
            "Pedro Sánchez",
            "Lucía Fernández",
            "Diego Torres",
            "Sofía Ramírez"
        )

        // Configurar adaptador
        val adaptadorSocios = AdaptadorSocios(listaSocios) { accion, socio ->
            when (accion) {
                "editar" -> {
                    val intent = Intent(this, AddEditSocioActivity::class.java)
                    intent.putExtra("SOCIO_NOMBRE", socio)
                    startActivity(intent)
                }
                "eliminar" -> Toast.makeText(this, "Eliminar: $socio", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerViewSocios.adapter = adaptadorSocios

        // Botón Vencimientos hoy
        val btnVencimientos = findViewById<Button>(R.id.btnVencimientos)
        btnVencimientos.setOnClickListener {
            val intent = Intent(this, ExpirationActivity::class.java)
            startActivity(intent)
        }

        // Botón Agregar socio
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AddEditSocioActivity::class.java)
            startActivity(intent)
        }

        // Botón Volver
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Cierra SociosActivity
        }
    }
}