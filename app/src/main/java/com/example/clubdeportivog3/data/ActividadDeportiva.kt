package com.example.clubdeportivog3.data

data class ActividadDeportiva(
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val horario: String = "",
    val monto: Double = 0.0,
    val cupoMaximo: Int = 0,
    val dia: String = ""
) {
    // Constructor secundario para compatibilidad con SocioDetailsActivity
    constructor(nombre: String, descripcion: String) : this(0, nombre, descripcion)
}