package com.example.exame_01.modelos

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class UsoDeporte {
    companion object {
        val listaatletas: MutableMap<Int, Deporte> = mutableMapOf()

        init {
            cargarDatos()
        }
        fun obtenerLista(): MutableMap<Int, Deporte> {
            return listaatletas
        }
        fun agregarDeporte(
            nombre:String, tiempoPromedioPartido: Float, numeroJugadores: Int =1,
            paralelo:String="Por editar", diaCum: Int? =null) {
            val deporte = Deporte(nombre,tiempoPromedioPartido,numeroJugadores)
            if (!listaatletas.containsValue(deporte)) {
                val newKey = if (listaatletas.isEmpty()) 1 else listaatletas.keys.maxOrNull()!! + 1
                listaatletas[newKey] = deporte
                println("Se agrego la deporte")
            } else {
                println("ERROR! Estudiante ya existente")
            }

        }

        fun obtenerIdDeporte(nombre: String, precio: Float): List<Int> {
            val idsEncontrados = listaatletas.entries
                .filter { it.value.nombre == nombre || it.value.tiempoPromedioPartido == precio  }
                .map { it.key }

            return if (idsEncontrados.isNotEmpty()) {
                println("Se encontraron deportes con el nombre $nombre. IDs: $idsEncontrados")
                idsEncontrados
            } else {
                println("No se encontraron deportes con el nombre $nombre")
                emptyList()
            }
        }




        fun editarDeporte(idDeporte: Int,
                          nuevoNombre: String? = null,
                          nuevoApellido: String? = null,
                          nuevoCurso: Int? = null) {
            val deporte = listaatletas[idDeporte]

            if (deporte != null) {
                // Actualizar solo los campos proporcionados
                nuevoNombre?.let { deporte.nombre = it }
                nuevoApellido?.let { deporte.tiempoPromedioPartido = it.toFloat() }
                nuevoCurso?.let { deporte.numeroJugadores = it}

                println("Deporte con ID $idDeporte editado exitosamente: $deporte")
            } else {
                println("Deporte no encontrado con el ID $idDeporte")
            }
        }

        fun eliminarEstudiante(idEstudiante: Int) {
            if (listaatletas.containsKey(idEstudiante)) {
                listaatletas.remove(idEstudiante)
                //guardarDatos()
            } else {
                println("Error: El estudiante a eliminar no existe.")
            }
        }

        fun verListaEstudiantes() {
            println("Lista de deportes y sus atletas:")
            for ((key, deporte) in listaatletas) {
                println("Deporte-ID:${key}: ${deporte.nombre} ${deporte.tiempoPromedioPartido}")
                if (deporte.atletas.isNotEmpty()){
                    deporte.verAtletas()
                }
                else{
                    println("Sin atletas")
                }

            }
        }


        private fun guardarDatos() {
            ObjectOutputStream(FileOutputStream("datos.txt")).use {
                it.writeObject(listaatletas)
                it.writeObject(listaatletas)
            }
        }

        private fun cargarDatos() {
            try {
                ObjectInputStream(FileInputStream("datos.txt")).use {
                    val estudiantesGuardados = it.readObject() as MutableMap<Int, Deporte>

                    listaatletas.putAll(estudiantesGuardados)

                }
            } catch (e: FileNotFoundException) {
                // Manejar la excepción si el archivo no existe (primera ejecución)
            }
        }
    }
}