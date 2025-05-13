package com.example.clubdeportivog3.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clubdeportivog3.R

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los elementos del layout
        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val button = findViewById<Button>(R.id.buttonIngresar)
        val registerText = findViewById<TextView>(R.id.textViewRegistrarse)

        // Acción al hacer clic en el botón "Ingresar"
        button.setOnClickListener {
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            if (userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
                Toast.makeText(this, "Bienvenido, $userEmail", Toast.LENGTH_SHORT).show()
                // Iniciar MenuActivity
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                // Opcional: finalizar SignInActivity para que no se pueda volver atrás
                finish()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción al hacer clic en el texto "Regístrate"
        registerText.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}