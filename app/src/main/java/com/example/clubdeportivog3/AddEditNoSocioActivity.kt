package com.example.clubdeportivog3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddEditNoSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_edit_no_socio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener referencias a los campos
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etDNI = findViewById<EditText>(R.id.etDNI)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etPagoDiario = findViewById<EditText>(R.id.etPagoDiario)
        val cbAptoFisico = findViewById<CheckBox>(R.id.cbAptoFisico)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Verificar si es edición (datos precargados)
        val noSocioNombre = intent.getStringExtra("NO_SOCIO_NOMBRE")
        if (!noSocioNombre.isNullOrEmpty()) {
            // Simulamos datos para edición (puedes reemplazar con datos reales de una BD)
            etNombre.setText(noSocioNombre.split(" ")[0]) // Nombre
            etApellido.setText(noSocioNombre.split(" ").getOrElse(1) { "" }) // Apellido
            // Aquí podrías precargar otros campos si tienes más datos
        }

        // Acción del botón Confirmar
        btnConfirmar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val dni = etDNI.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val pagoDiario = etPagoDiario.text.toString().trim()
            val aptoFisico = cbAptoFisico.isChecked

            // Validaciones básicas
            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || correo.isEmpty() || telefono.isEmpty() || pagoDiario.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simular guardado (puedes conectar con una base de datos aquí)
            Toast.makeText(
                this,
                "No Socio: $nombre $apellido\nDNI: $dni\nCorreo: $correo\nTeléfono: $telefono\nPago diario: $$pagoDiario\nApto físico: $aptoFisico",
                Toast.LENGTH_LONG
            ).show()

            // Volver a NoSociosActivity
            val intent = Intent(this, NoSocioListActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Acción del botón Volver
        btnVolver.setOnClickListener {
            val intent = Intent(this, NoSocioListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}