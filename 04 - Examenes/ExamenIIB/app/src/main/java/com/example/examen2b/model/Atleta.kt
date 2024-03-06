package com.example.examenib.model

import java.text.SimpleDateFormat
import java.util.Date

class Atleta(
    var id: String,
    var nombre: String,
    var descripcion: String,
    var precio: Long,
    var fechaCreacion: Date,
    var deporte: MutableList<Deporte>? = null
) {
    override fun toString(): String {
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

        // Formatea la fecha a una cadena en el formato especificado
        val fechaFormateada = formatoFecha.format(fechaCreacion)

        return "Producto:\nID:$id\nNombre:$nombre\nDescripcion:$descripcion\nPrecio:$precio\nFechaCreacion:$fechaFormateada"
    }
}