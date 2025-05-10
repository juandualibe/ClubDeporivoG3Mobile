package com.example.clubdeportivog3

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddEditActividadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_edit_actividad)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos del layout
        val editNombre = findViewById<EditText>(R.id.editNombre)
        val editHorario = findViewById<EditText>(R.id.editHorario)
        val editMonto = findViewById<EditText>(R.id.editMonto)
        val editCupo = findViewById<EditText>(R.id.editCupo)
        val spinnerDia = findViewById<Spinner>(R.id.spinnerDia)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Configurar Spinner para días de la semana
        val diasSemana = arrayOf("Día de la semana", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, diasSemana)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDia.adapter = adapter

        // Verificar si es modo edición
        val modo = intent.getStringExtra("MODO")
        if (modo == "editar") {
            editNombre.setText(intent.getStringExtra("ACTIVIDAD_NOMBRE"))
            editHorario.setText(intent.getStringExtra("ACTIVIDAD_HORARIO"))
            editMonto.setText(intent.getDoubleExtra("ACTIVIDAD_MONTO", 0.0).toString())
            editCupo.setText(intent.getIntExtra("ACTIVIDAD_CUPO", 0).toString())
            val dia = intent.getStringExtra("ACTIVIDAD_DIA")
            if (dia != null) {
                val posicionDia = diasSemana.indexOf(dia)
                if (posicionDia != -1) spinnerDia.setSelection(posicionDia)
            }
        }

        // Botón Volver
        btnVolver.setOnClickListener {
            val intent = Intent(this, ActividadesListActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Botón Confirmar
        btnConfirmar.setOnClickListener {
            val nombre = editNombre.text.toString()
            val horario = editHorario.text.toString()
            val monto = editMonto.text.toString().toDoubleOrNull() ?: 0.0
            val cupo = editCupo.text.toString().toIntOrNull() ?: 0
            val dia = spinnerDia.selectedItem.toString()

            if (nombre.isEmpty() || horario.isEmpty() || monto == 0.0 || cupo == 0 || dia == "Día de la semana") {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Actividad: $nombre, Horario: $horario, Monto: $$monto, Cupo: $cupo, Día: $dia",
                    Toast.LENGTH_LONG
                ).show()
                // Aquí iría la lógica para guardar la actividad (base de datos, etc.)
                finish() // Cierra la actividad después de confirmar
            }
        }
    }
}