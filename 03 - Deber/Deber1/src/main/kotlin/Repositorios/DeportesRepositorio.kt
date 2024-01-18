package Repositorios

import Entidades.Atleta
import Entidades.Deporte
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

class DeportesRepositorio : Repositorio() {
        private val deportes: MutableMap<Int, Deporte> = mutableMapOf()
        private val atletasRepositorio: AtletasRepositorio = AtletasRepositorio()

        override var rutaRepositorio: String = "src/main/kotlin/Datos/deportes.txt"

        init {
                cargarDatos()
        }

        override fun cargarDatos() {
                val file = File(rutaRepositorio)
                var counter: Int = 0
                if (file.exists()) {
                        file.forEachLine {
                                val separacion = it.split(",")
                                if (separacion.size >= 5) {
                                        val nombre = separacion[0]
                                        val deporteIndividual = separacion[1].toBoolean()
                                        val tiempoPromedioPartido = separacion[2].toFloat()
                                        val fechaRegistro = Date(separacion[3].toLong())
                                        val numeroJugadoresEquipo = separacion[4].toInt()
                                        val deporte = Deporte(
                                                nombre,
                                                deporteIndividual,
                                                tiempoPromedioPartido,
                                                fechaRegistro,
                                                numeroJugadoresEquipo
                                        )

                                        deportes[counter++] = deporte

                                        if (separacion.size > 5) {
                                                for (i in 5 until separacion.size) {

                                                        val nombreAtleta = separacion[i]

                                                        val atleta = atletasRepositorio.obtenerAtletaPorNombre(nombreAtleta)
                                                        if (atleta != null) {
                                                                deporte.atletas += atleta
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }

        override fun guardarDatos() {
                val file = File(rutaRepositorio)
                FileWriter(file, false).use { fileWriter ->
                        BufferedWriter(fileWriter).use { out ->
                                deportes.values.forEach { deporte ->
                                        val listaNombresAtletas = deporte.atletas.joinToString(",") { it.nombre }
                                        out.write(
                                                "${deporte.nombre}," +
                                                "${deporte.esDeporteIndividual}," +
                                                "${deporte.tiempoPromedioPartido}," +
                                                "${deporte.fechaRegistro.time}," +
                                                "${deporte.numeroJugadores}," +
                                                "$listaNombresAtletas\n"
                                        )
                                }
                        }
                }
        }

        fun agregarDeporte(deporte: Deporte) {
                deportes[deportes.size] = deporte
                guardarDatos()
        }

        fun obtenerDeportePorNombre(nombre: String): Deporte? {
                return deportes.values.find { it.nombre == nombre }
        }

        fun eliminarDeportePorNombre(nombre: String) {
                val idAEliminar = deportes.entries.find { it.value.nombre == nombre }?.key
                idAEliminar?.let { deportes.remove(it) }
                guardarDatos()
        }

        fun obtenerDeportes(): List<Deporte> {
                return deportes.values.toList()
        }

        fun actualizarDeporte(nombre: String, deporte: Deporte) {
                val deporteActualizar = deportes.entries.find { it.value.nombre == nombre }

                if (deporteActualizar != null) {
                        deportes[deporteActualizar.key] = deporte
                        guardarDatos()
                }
        }

        fun agregarAtleta(deporte: String, atleta: Atleta) {
                val deporteActualizar = deportes.entries.find { it.value.nombre == deporte }

                if (deporteActualizar != null) {
                        deportes[deporteActualizar.key]?.atletas?.add(atleta)
                        guardarDatos()
                }
        }

        fun eliminarAtleta(deporte: String, atleta: Atleta) {
                val deporteActualizar = deportes.entries.find { it.value.nombre == deporte }

                if (deporteActualizar != null) {
                        deportes[deporteActualizar.key]?.atletas?.removeIf { it.nombre == atleta.nombre }
                        guardarDatos()
                }
        }
}
