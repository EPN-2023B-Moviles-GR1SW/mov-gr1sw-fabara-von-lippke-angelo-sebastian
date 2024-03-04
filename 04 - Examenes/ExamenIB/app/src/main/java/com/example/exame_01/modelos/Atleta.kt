package com.example.exame_01.modelos

import java.util.Date

class Atleta(
    val nombre: String,
    val habilitadoParaParticipar: Boolean?,
    var altura: Double,
    val edad: Int?,
    var fechaNacimiento: Date?

    ) {
        constructor(nombre: String,habilitadoParaParticipar: Boolean?,altura: Double,edad: Int?):this(
            nombre,
            habilitadoParaParticipar,
            altura,
            edad,
            Date()
        )
        constructor(): this("faltante",false,0.0,0)
    }
