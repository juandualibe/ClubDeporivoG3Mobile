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

class AddEditSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_edit_socio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos del layout
        val editNombre = findViewById<EditText>(R.id.editNombre)
        val editApellido = findViewById<EditText>(R.id.editApellido)
        val editDni = findViewById<EditText>(R.id.editDni)
        val editCorreo = findViewById<EditText>(R.id.editCorreo)
        val editTelefono = findViewById<EditText>(R.id.editTelefono)
        val editCuota = findViewById<EditText>(R.id.editCuota)
        val checkAptoFisico = findViewById<CheckBox>(R.id.checkAptoFisico)
        val checkCarnetEntregado = findViewById<CheckBox>(R.id.checkCarnetEntregado)
        val checkPagoAlDia = findViewById<CheckBox>(R.id.checkPagoAlDia)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Botón Volver
        btnVolver.setOnClickListener {
            val intent = Intent(this, SocioListActivity::class.java)
            startActivity(intent)
            finish() // Cierra AddEditSocioActivity
        }

        // Botón Confirmar
        btnConfirmar.setOnClickListener {
            val nombre = editNombre.text.toString()
            val apellido = editApellido.text.toString()
            val dni = editDni.text.toString()
            val correo = editCorreo.text.toString()
            val telefono = editTelefono.text.toString()
            val cuota = editCuota.text.toString().toDoubleOrNull() ?: 0.0
            val aptoFisico = checkAptoFisico.isChecked
            val carnetEntregado = checkCarnetEntregado.isChecked
            val pagoAlDia = checkPagoAlDia.isChecked

            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                Toast.makeText(this, "Por favor, completa los campos obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                // Abrir AddedEditedSocioActivity
                val intent = Intent(this, AddedEditedSocioActivity::class.java)
                startActivity(intent)
                finish() // Cierra AddEditSocioActivity
            }
        }
    }
}