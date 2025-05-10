package com.example.clubdeportivog3

data class ActividadDeportiva(
    val nombre: String,
    val descripcion: String,
    val horario: String = "",
    val monto: Double = 0.0,
    val cupoMaximo: Int = 0,
    val dia: String = ""
)