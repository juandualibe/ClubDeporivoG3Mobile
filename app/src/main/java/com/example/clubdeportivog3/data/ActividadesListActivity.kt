package com.example.clubdeportivog3.data

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clubdeportivog3.R
import com.example.clubdeportivog3.activities.AddEditActividadActivity
import com.example.clubdeportivog3.activities.DeletedActivity
import com.example.clubdeportivog3.activities.MenuActivity

class ActividadesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar RecyclerView
        val recyclerViewActividades = findViewById<RecyclerView>(R.id.recyclerViewActividades)
        recyclerViewActividades.layoutManager = LinearLayoutManager(this)

        // Lista de actividades ficticia con id
        val listaActividades = listOf(
            ActividadDeportiva(1, "Fútbol", "Partidos semanales en cancha sintética", "Lunes 18:00-20:00", 15000.0, 20, "Lunes"),
            ActividadDeportiva(2, "Básquet", "Entrenamiento y torneos locales", "Miércoles 19:00-21:00", 12000.0, 15, "Miércoles"),
            ActividadDeportiva(3, "Natación", "Clases para todas las edades", "Viernes 17:00-18:30", 18000.0, 10, "Viernes"),
            ActividadDeportiva(4, "Yoga", "Sesiones de relajación y estiramiento", "Martes 08:00-09:30", 10000.0, 12, "Martes"),
            ActividadDeportiva(5, "Tenis", "Canchas disponibles para práctica", "Jueves 16:00-18:00", 20000.0, 8, "Jueves"),
            ActividadDeportiva(6, "Vóley", "Entrenamiento en playa y gimnasio", "Sábado 10:00-12:00", 13000.0, 16, "Sábado")
        )

        // Configurar adaptador
        val adaptadorActividades = AdaptadorActividades(listaActividades) { accion, actividad ->
            when (accion) {
                "editar" -> {
                    val intent = Intent(this, AddEditActividadActivity::class.java).apply {
                        putExtra("ACTIVIDAD_ID", actividad.id)
                        putExtra("ACTIVIDAD_NOMBRE", actividad.nombre)
                        putExtra("ACTIVIDAD_DESCRIPCION", actividad.descripcion)
                        putExtra("ACTIVIDAD_HORARIO", actividad.horario)
                        putExtra("ACTIVIDAD_MONTO", actividad.monto)
                        putExtra("ACTIVIDAD_CUPO", actividad.cupoMaximo)
                        putExtra("ACTIVIDAD_DIA", actividad.dia)
                        putExtra("MODO", "editar")
                    }
                    startActivity(intent)
                }
                "eliminar" -> {
                    AlertDialog.Builder(this)
                        .setMessage("¿Está seguro que desea eliminar esta actividad?")
                        .setNegativeButton("No") { _, _ ->
                            // No hacer nada, permanecer en ActividadesListActivity
                        }
                        .setPositiveButton("Sí") { _, _ ->
                            // Navegar a DeletedActivity
                            val intent = Intent(this, DeletedActivity::class.java)
                            startActivity(intent)
                            // No cerramos ActividadesListActivity para permitir volver atrás
                        }
                        .setCancelable(true)
                        .show()
                }
            }
        }
        recyclerViewActividades.adapter = adaptadorActividades

        // Botón Agregar actividad
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AddEditActividadActivity::class.java).apply {
                putExtra("MODO", "añadir")
            }
            startActivity(intent)
        }

        // Botón Volver
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}