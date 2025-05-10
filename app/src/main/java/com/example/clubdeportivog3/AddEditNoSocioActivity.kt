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

        // Referencias a los elementos del layout
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etDNI = findViewById<EditText>(R.id.etDNI)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etPagoDiario = findViewById<EditText>(R.id.etPagoDiario)
        val cbAptoFisico = findViewById<CheckBox>(R.id.cbAptoFisico)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Verificar si es modo edición
        val noSocioNombre = intent.getStringExtra("NO_SOCIO_NOMBRE")
        if (noSocioNombre != null) {
            // Modo edición: precargar datos
            val nombreApellido = noSocioNombre.split(" ")
            etNombre.setText(nombreApellido[0])
            etApellido.setText(nombreApellido.getOrNull(1) ?: "")
            // Otros campos podrían precargarse si tienes más datos en el Intent
        }

        // Botón Volver
        btnVolver.setOnClickListener {
            val intent = Intent(this, NoSocioListActivity::class.java)
            startActivity(intent)
            finish() // Cierra AddEditNoSocioActivity
        }

        // Botón Confirmar
        btnConfirmar.setOnClickListener {
            // Variables de ejemplo para pequeña validación
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val dni = etDNI.text.toString()

            // ESTAS VARIABLES LAS VAMOS A USAR DESPUÉS PARA HACER VALIDACIONES MAS FUERTES!
            val correo = etCorreo.text.toString()
            val telefono = etTelefono.text.toString()
            val pagoDiario = etPagoDiario.text.toString().toDoubleOrNull() ?: 0.0
            val aptoFisico = cbAptoFisico.isChecked

            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                Toast.makeText(this, "Por favor, completa los campos obligatorios (Nombre, Apellido, DNI)", Toast.LENGTH_SHORT).show()
            } else {
                // Abrir AddedEditedNoSocioActivity
                val intent = Intent(this, AddedEditedNoSocioActivity::class.java)
                startActivity(intent)
                finish() // Cierra AddEditNoSocioActivity
            }
        }
    }
}