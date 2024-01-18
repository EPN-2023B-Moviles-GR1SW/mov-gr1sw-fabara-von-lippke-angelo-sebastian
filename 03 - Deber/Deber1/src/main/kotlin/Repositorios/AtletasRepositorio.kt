package Repositorios

import Entidades.Atleta
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

class AtletasRepositorio : Repositorio() {
        private val atletas: MutableMap<Int, Atleta> = mutableMapOf()
        override var rutaRepositorio: String = "src/main/kotlin/Datos/atletas.txt"

        init {
        cargarDatos()
        }

        override fun cargarDatos() {
                val file = File(rutaRepositorio)
                var i: Int = 0
                if (file.exists()) {
                        file.forEachLine {
                                val separacion = it.split(",")
                                if (separacion.size >= 5) {
                                        val nombre = separacion[0]
                                        val habilitadoParaParticipar = separacion[1].toBoolean()
                                        val altura = separacion[2].toFloat()
                                        val fechaNacimiento = Date(separacion[3].toLong())
                                        val edad = separacion[4].toInt()
                                        atletas[i++] = Atleta(
                                                nombre,
                                                habilitadoParaParticipar,
                                                altura,
                                                fechaNacimiento,
                                                edad
                                        )
                                }
                        }
                }
        }

        override fun guardarDatos() {
                val file = File(rutaRepositorio)
                FileWriter(file, false).use { fileWriter ->
                        BufferedWriter(fileWriter).use { out ->
                                atletas.values.forEach {
                                        out.write("${it.nombre},${it.habilitadoParaParticipar},${it.altura},${it.fechaNacimiento.time},${it.edad}\n")
                                }
                        }
                }
        }

        fun agregarAtleta(atleta: Atleta) {
                atletas[atletas.size] = atleta
                guardarDatos()
        }

        fun obtenerAtletaPorNombre(nombre: String): Atleta? {
                return atletas.values.find { it.nombre == nombre }
        }

        fun eliminarAtletaPorNombre(nombre: String) {
                val idEliminar = atletas.entries.find { it.value.nombre == nombre }?.key
                idEliminar?.let { atletas.remove(it) }
                guardarDatos()
        }

        fun actualizarAtleta(nombre: String, atleta: Atleta) {
                val atletaActualizar = atletas.entries.find { it.value.nombre == nombre }

                if (atletaActualizar != null) {
                        atletas[atletaActualizar.key] = atleta
                        guardarDatos()
                }
        }

        fun obtenerAtletas(): List<Atleta> {
                return atletas.values.toList()
        }
}
