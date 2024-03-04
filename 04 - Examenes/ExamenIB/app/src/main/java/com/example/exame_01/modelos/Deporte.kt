package com.example.exame_01.modelos

import java.util.Date

class Deporte (
    var nombre: String,
    var esDeporteIndividual: Boolean?,
    var tiempoPromedioPartido: Float,
    val fechaRegistro: Date,
    var numeroJugadores: Int,
    val atletas: MutableList<Atleta>
    ) {
        constructor(nombre: String, esDeporteIndividual: Boolean, tiempoPromedioPartido: Float, numeroJugadores: Int) :
        this(nombre, esDeporteIndividual, tiempoPromedioPartido, Date(), numeroJugadores,mutableListOf())

        constructor(nombre: String, tiempoPromedioPartido: Float, numeroJugadores: Int) :
        this(nombre, false, tiempoPromedioPartido, Date(), numeroJugadores,mutableListOf())

        fun obtenerAtletas(): MutableList<Atleta> {
            return atletas
        }
        fun verAtletas() {
            if(atletas.isNotEmpty()){
                atletas.forEach{
                    println(it.nombre)
                }
            }else{
                println("No se tienen Atletas asociados")
            }



        }

        // Método para agregar una calificación
        fun agregarAtleta(nombre: String, altura: Double, edad : Int) {
            val nuevoAtleta = Atleta(nombre,null,altura,edad)
            atletas.add(nuevoAtleta)
            println("Atleta agregado: Atleta: $nombre, Altura: $altura")
        }

        // Método para editar una calificación por materia
        fun editarAtleta(nombre: String, nuevaAltura: Double) {
            val atleta = atletas.find { it.nombre == nombre }
            if (atleta != null) {
                atleta.altura = nuevaAltura
                println("Atleta editado: Atleta: $nombre, Nueva altura: $nuevaAltura")
            } else {
                println("No se encontró el Atleta $nombre para editarlo")
            }
        }

        // Método para eliminar una calificación por materia
        fun eliminarAtleta(nombre: String) {
            val atleta = atletas.find { it.nombre == nombre }
            if (atleta != null) {
                atletas.remove(atleta)
                println("Atleta eliminado: Atleta: $nombre")
            } else {
                println("No se encontró el atleta $nombre para eliminarlo")
            }
        }

    }